# RaulPachecoo-GestionBibliotecaSpringBoot

# **INTRODUCCIÓN**
El objetivo de este proyecto es realizar un microservicio API REST con el framework SpringBoot para el backend y que además contenga una interfaz que en nuestro caso hemos realizado utilizando PHP. El servicio API REST contiene todas las operaciones CRUD utilizadas en la interfaz, realizando además la conexión a la base de datos y guardando el historial de todas las operaciones en archivos.

Hemos realizado la organización del trabajo utilizando metodologías ágiles, en concreto la metodología SCRUM. Para ello hemos utilizado la herramienta ClickUp y hemos designado un SCRUM Manager que ha sido Raúl. En las diferentes reuniones realizadas en clase hemos debatido la partición del trabajo y posteriormente cada uno ha trabajado individualmente en la tarea que se le asignó.
## **CONEXIÓN A LA BASE DE DATOS**
Hemos realizado la conexión a la base de datos utilizando el fichero application.propertis, que contiene la información necesaria para configurar esta conexión.

![Texto alternativo](https://files.catbox.moe/v1z0vp.png)

## **ENTIDADES**
Una vez configurada la conexión con la base de datos, las entidades han sido generadas automáticamente con anotaciones JPA. Estas entidades contienen solamente los atributos de cada tabla y los métodos getters y setters. Para las relaciones entre tablas hemos utilizado listas y objetos apropiadamente.
## **REPOSITORIOS**
Los repositorios son interfaces vacías que extienden de la interfaz CrudRepository proporcionada por Spring Data, que incluye métodos para operaciones CRUD (Crear, Leer, Borrar y Actualizar). Es decir estos repositorios contienen métodos predefinidos que nos facilitan el trabajo a la hora de trabajar con bases de datos.
## **CONTROLADORES**
Los controladores son las clases encargadas de manejar las GET, PUT, POST y DELETE. Es decir, el controlador accede al repositorio correspondiente a esa tabla para realizar las distintas operaciones sobre la base de datos. Además los controladores guardan las acciones realizadas en la tabla histórico y en un archivo.
## **INTERFAZ**
La interfaz consiste en una pantalla destinada a realizar el inicio de sesión en la aplicación, junto con otras pantallas diseñadas para llevar a cabo diversas operaciones de forma interactiva sobre la base de datos.
### **LOGIN**

### **PANTALLA PRINCIPAL**
Esta pantalla solamente contiene el menú de navegación para moverse por las distintas tablas y un botón para cerrar sesión.
### **PANTALLAS DE LECTURA**
Estas pantallas contienen la información de las distintas tablas, préstamos, libros…
### **FORMULARIOS DE INSERCIÓN Y ACTUALIZACIÓN**
Formularios para insertar y modificar los datos de una fila en la base de datos.
### **MÉTODOS CRUD**
Métodos utilizados para realizar las peticiones al servicio de API REST al realizar las operaciones CRUD. Cada tabla tiene unos métodos propios para insertar, borrar y actualizar. Este es un ejemplo, de los métodos utilizados para las operaciones con la tabla Libro.
