# Documentación del Proyecto: Biblioteca API

# INCOMPLETO, NO LO PUDE TERMINAR POR CUESTIONES TECNICAS.
## Lo agrege por que esta bien adelantado. Solo me falto Pruebas Unitarias y  Autenticación y Autorización: Implementar la autenticación de usuarios utilizando JWT.Proteger las rutas de la API que requieren autenticación.

## Descripción

Este proyecto es una API RESTful para la gestión de una biblioteca de libros. La API permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre libros y usuarios. Está construida con Spring Boot y utiliza JWT para la autenticación de usuarios.

## Estructura del Proyecto

1. **Dependencias**:
   - **Spring Boot**: Framework principal para el desarrollo de la aplicación.
   - **Spring Data JPA**: Para la persistencia de datos.
   - **Spring Security**: Para la seguridad y autenticación.
   - **MySQL**: Base de datos relacional.
   - **JUnit 5 y Mockito**: Para pruebas unitarias y de integración.
   - **Jacoco**: Para la cobertura de pruebas.

2. **Entidades**:
   - **User**: Representa a un usuario en el sistema.
   - **Book**: Representa un libro en la biblioteca.

3. **DTOs**:
   - **UserDTO**: Datos transferidos relacionados con un usuario.
   - **BookDTO**: Datos transferidos relacionados con un libro.

4. **Servicios y Repositorios**:
   - **UserService**: Gestiona la lógica de negocio relacionada con los usuarios.
   - **BookService**: Gestiona la lógica de negocio relacionada con los libros.
   - **UserRepository**: Interfaz para la persistencia de usuarios.
   - **BookRepository**: Interfaz para la persistencia de libros.

5. **Controladores**:
   - **UserController**: Expones endpoints para la gestión de usuarios.
   - **BookController**: Expones endpoints para la gestión de libros.

6. **Configuración de Seguridad**:
   - **SecurityConfig**: Configura la seguridad de la aplicación utilizando JWT para la autenticación.



## Documentación de API
### Endpoints de Usuarios
- POST /users/register: Registrar un nuevo usuario.
- POST /users/login: Autenticar un usuario y obtener un JWT.
- GET /users/profile: Obtener el perfil del usuario autenticado.

### Endpoints de Libros
- POST /books: Crear un nuevo libro.
- GET /books: Obtener la lista de libros.
- GET /books/{id}: Obtener los detalles de un libro específico.
- PUT /books/{id}: Actualizar la información de un libro.
- DELETE /books/{id}: Eliminar un libro.

## Instrucciones para la Ejecución

1. **Clonar el Repositorio**:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd library-api
