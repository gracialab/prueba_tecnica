# Documentación del Proyecto Biblioteca API

## Índice

1. [Introducción](#introducción)
2. [Requisitos](#requisitos)
3. [Configuración del Entorno](#configuración-del-entorno)
4. [Ejecución del Proyecto](#ejecución-del-proyecto)
5. [Estructura del Proyecto](#estructura-del-proyecto)
6. [Descripción de las Vistas](#descripción-de-las-vistas)
7. [Pruebas](#pruebas)


## Introducción

Este proyecto es una aplicación web de gestión de biblioteca construida con Django. La aplicación permite a los usuarios realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los libros y gestionar usuarios.

## Requisitos

Para ejecutar este proyecto, necesitas tener instalados los siguientes requisitos:

- Python 3.8 o superior
- Django 4.2.15
- Django REST Framework
- drf-yasg (para la documentación Swagger)
- MySQL (o una base de datos compatible)
- pip (gestor de paquetes de Python)

## Configuración del Entorno

1. **Clona el Repositorio**

   Si aún no lo has hecho, clona el repositorio del proyecto:

   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd <NOMBRE_DEL_PROYECTO>

2. **Crea un Entorno Virtual**
    ```
    python3 -m venv env
    source env/bin/activate


3. **Instala las Dependencias**
    ```
    pip install -r requirements.txt
    
4. **Configura la Base de Datos**
    
    Asegúrate de que la base de datos esté configurada en el archivo settings.py. Ejemplo para MySQL:
    ```
    DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': 'nombre_de_la_base_de_datos',
        'USER': 'usuario',
        'PASSWORD': 'contraseña',
        'HOST': 'localhost',
        'PORT': '3306', 
        }
     }

5. **Aplica las Migraciones**
    ```
     python manage.py migrate

6. **Crea un Superusuario**
    ```
    python manage.py    createsuperuser


## Ejecución del Proyecto

1. Inicia el Servidor de Desarrollo
    ```
     python manage.py runserver


 ##  Estructura del Proyecto
El proyecto sigue una estructura organizada para facilitar el desarrollo y mantenimiento:
```
 biblioteca/
    manage.py
    biblioteca/
        __init__.py
        settings.py
        urls.py
        wsgi.py
    gestion/
        __init__.py
        models.py
        views.py
        urls.py
        forms.py
        templates/
            home.html
            book_list.html
            book_detail.html
            book_form.html
            book_confirm_delete.html
            api_root.html
            user_create.html
    requirements.txt
```



- biblioteca/settings.py: Configuración del proyecto Django.

- biblioteca/urls.py: Configuración de las URLs principales del proyecto.
- gestion/models.py: Modelos de datos para los libros y usuarios.
- gestion/views.py: Vistas que manejan las peticiones HTTP.
- gestion/urls.py: Rutas de la aplicación de gestión.
- gestion/forms.py: Formularios para crear y editar libros.
- gestion/templates/: Plantillas HTML para las vistas.


## Descripción de las Vistas

- HomeView: Redirige al usuario a la página de inicio de la API.
- UserCreateView: Vista para crear un nuevo usuario.
- ApiRootView: Vista que muestra la página principal de la API.
- BookListView: Muestra una lista de todos los libros.
- BookDetailView: Muestra los detalles de un libro específico.
- BookCreateView: Permite crear un nuevo libro.
- BookUpdateView: Permite actualizar un libro existente.
- BookDeleteView: Permite eliminar un libro.


## Pruebas
Para ejecutar pruebas unitarias y de integración, utiliza los siguientes comandos:

 1. Pruebas Unitarias

    ```
    python manage.py test

 2. Pruebas de Integración

    Asegúrate de que todas las pruebas de integración cubran las rutas y vistas del proyecto.

