# Proyecto [Nombre del Proyecto]

## Descripción

Este proyecto es una API RESTful para la gestion de una biblioteca de libros,la api permite la gestion de usuarios,libros y la autenticacion para acceder a ciertas funcionalidades.Se utilizan DTOs para transferir datos entre el cliente y el servidor, y entre capas del sistema,usa una base de datos mysql para almacenar libros y usuarios utilizando el acceso a datos mediante Spring Data JPA.Se implementa la autenticación de usuarios utilizando Spring security Protegiendo las rutas de la API que requieren autenticación.Sigue principios de arquitectura limpia y moderna, separando claramente las capas de presentación, negocio y acceso a datos.

## Requisitos

Spring Web
Spring Data JPA
Spring Security
MySQL
Spring Boot DevTools
Java JDK 17
Maven


## Instalación

### Clonar el Repositorio

https://github.com/elkinguerrero007/prueba_tecnica.git


#PRUEBAS LOCALES A TRAVES DE COMANDOS CURL 


REGISTRAR USUARIO


curl -X POST http://localhost:8080/api/register \
      -H "Content-Type: application/json" \
      -d '{
            "username": "elkin",
            "email": "elkin.10041@gmail.com",
            "password": "123"
          }'


LOGIN


curl -X POST http://localhost:8080/api/login \
     -H "Content-Type: application/json" \
     -d '{
           "username": "elkin",
           "password": "123"
         }'


PERFIL



curl -X GET http://localhost:8080/api/profile -H "Authorization: Basic ZWxraW46MTIz"



INSERTAR UN LIBRO



curl -X POST http://localhost:8080/api/save \
    -H "Content-Type: application/json" \
    -H "Authorization: Basic ZWxraW46MTIz" \
    -d '{
      "titulo": "El Gran Gatsby",
      "autor": "F. Scott Fitzgerald",
      "genero": "Ficción"
}'


OBTENER TODOS LOS LIBROS

curl -X GET http://localhost:8080/api/getAll


OBTENER LIBRO POR ID


curl -X GET http://localhost:8080/api/getById/1



ACTUALIZAR LIBRO POR ID

curl -X PUT http://localhost:8080/api/update/1 \
     -H "Content-Type: application/json" \
     -H "Authorization: Basic ZWxraW46MTIz" \
     -d '{
           "titulo": "El Gran Gatsby - Actualizado",
           "autor": "F. Scott Fitzgerald",
           "genero": "Ficción"
         }'


BORRAR LIBRO POR ID

curl -X DELETE http://localhost:8080/api/delete/1      -H "Authorization: Basic ZWxraW46MTIz"


