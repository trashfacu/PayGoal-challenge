[![Github Status][github-shield]][github-url]

## PayGoal Challenge.

En la carpeta `assets` hay un archivo llamado `testPostman.postman_collection.json`,en ella están todas las pruebas a los endpoints de la API.

Documentación automática con swagger : [http://localhost:8080/api/swagger/swagger-ui/index.html](http://localhost:8080/api/swagger/swagger-ui/index.html) y tambien está la sección de [Endpoints](#endpoints)

## Pre-requisitos

+ Maven
+ Java 17
+ H2 database

## Deployment

Para inicializar el proyecto de forma local primero clonamos el mismo y nos posicionamos en la carpeta para ejecutar los siguientes comandos:

```bash
  mvn clean
```
```bash
  mvn install -DskipTests
```

O mismamente abrirlo en un IDE (Intellij por ejemplo) y ejecutarlo.

## Endpoints

### Producto

- Registrar nuevo: `POST` a `PATH/api/products`
  - `201 OK` → se registró correctamente
  - `400 BAD REQUEST` → hubo un error en los datos recibidos
    ```json
    {
        "productName" : "producto",
        "productDescription" : "descripcion",
        "productPrice" : "120",
        "productStock" : "24"
    }
    ```
- Buscar por id: `GET` a `PATH/api/products/{id}`

  - `200 OK` → devuelve el producto
  - `404 NOT FOUND` → no se encontró un producto con ese id

- Actualizar existente: `PUT` a `/api/products/1`
  - `200 OK` → se actualizó correctamente
  - `400 BAD REQUEST` → hubo un error en los datos recibidos
  - `404 NOT FOUND` → no se encontró el producto con id recibido
    ```json
    {
        "productName" : "nombre nuevo",
        "productDescription" : "descripcion nueva",
        "productPrice" : "121",
        "productStock" : "25"
    }
    ```
- Eliminar por id: `DELETE` a `PATH/api/products/delete/{id}`

  - `200 NO CONTENT` → se borró correctamente
  - `404 NOT FOUND` → no se encontró el producto con id recibido

- Obtener todos ordenados por precio: `GET` a `PATH/api/products/list`


[github-shield]: https://img.shields.io/badge/GitHub-trashfacu-blue?logo=github&style=flat
[github-url]: https://github.com/trashfacu/RantMyGameAPI