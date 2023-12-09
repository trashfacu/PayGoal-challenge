package com.facundom.payGoal.service;

import com.facundom.payGoal.entity.Product;
import com.facundom.payGoal.model.ProductDTO;
import com.facundom.payGoal.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements CRUDService<ProductDTO, Product> {

    private static final Logger logger = Logger.getLogger(Product.class);
    private final ProductRepository repository;
    private final ObjectMapper mapper;

    @Override
    public void create(ProductDTO productDTO) throws Exception {
        if (productDTO == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product can't be null");
        }
        if (productDTO.getProductPrice() <= 0){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Price cant be below than 0");
        }
        try {
            Product product = mapper.convertValue(productDTO, Product.class);
            repository.save(product);
        }catch (Exception e){
            logger.error("An error occurred while saving a product.");
            throw e;
        }
    }

    @Override
    public ProductDTO read(Integer id) throws Exception {
        if (id == null || id <= 0){
            throw new IllegalArgumentException("Invalid product id");
        }
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return mapper.convertValue(product, ProductDTO.class);
    }

    @Override
    public void delete(Integer id) throws Exception {
        if (id == null || id <= 0){
            throw new IllegalArgumentException("Invalid product id");
        }
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        repository.deleteById(id);
    }

    @Override
    public void update(ProductDTO productDTO) throws Exception {
        if (productDTO == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product can't be null");
        }

        Integer productId = productDTO.getProductId();
        if (productId == null || productId <= 0){
            throw new IllegalArgumentException("Invalid product Id.");
        }

        if (!repository.existsById(productId)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        Product product = mapper.convertValue(productDTO, Product.class);
        product.setProductId(productId);
        repository.save(product);
    }

    public List<ProductDTO> getAllSortedByPrice(){
        List<Product> productList = repository.findAllByOrderByProductPriceDesc();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (Product product : productList){
            productDTOList.add(mapper.convertValue(product, ProductDTO.class));
        }
        return productDTOList;
    }

    public List<ProductDTO> getAllSortedByPriceStream(){

        return repository.findAllByOrderByProductPriceDesc().stream()
                .map(product -> mapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

}
