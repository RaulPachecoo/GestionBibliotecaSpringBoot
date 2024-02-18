# GestionBibliotecaSpringBoot

## Índice

1. [**INTRODUCCIÓN**](#introducción)
   - Objetivo del proyecto
   - Metodología utilizada

2. [**CONEXIÓN A LA BASE DE DATOS**](#conexión-a-la-base-de-datos)
   - Configuración de la conexión
   - ![Imagen: Conexión a la base de datos](https://files.catbox.moe/v1z0vp.png)

3. [**ENTIDADES**](#entidades)
   - Generación automática con JPA
   - Imágenes de las entidades

4. [**REPOSITORIOS**](#repositorios)
   - Descripción de los repositorios
   - Imágenes de los repositorios

5. [**CONTROLADORES**](#controladores)
   - Funciones y responsabilidades
   - Imágenes de los controladores

6. [**INTERFAZ**](#interfaz)
   - Descripción general
   - Pantalla de Login
   - Pantalla Principal
   - Pantallas de Lectura
   - Formularios de Inserción y Actualización

7. [**MÉTODOS CRUD**](#métodos-crud)
   - Descripción y ejemplos de los métodos CRUD

8. [**CONCLUSIÓN**](#conclusión)
   - Aprendizajes obtenidos
   - Valoración del proyecto

# **INTRODUCCIÓN**
El objetivo de este proyecto es realizar un microservicio API REST con el framework SpringBoot para el backend y que además contenga una interfaz que en nuestro caso hemos realizado utilizando PHP. El servicio API REST contiene todas las operaciones CRUD utilizadas en la interfaz, realizando además la conexión a la base de datos y guardando el historial de todas las operaciones en archivos.

Hemos realizado la organización del trabajo utilizando metodologías ágiles, en concreto la metodología SCRUM. Para ello hemos utilizado la herramienta ClickUp y hemos designado un SCRUM Manager que ha sido Raúl. En las diferentes reuniones realizadas en clase hemos debatido la partición del trabajo y posteriormente cada uno ha trabajado individualmente en la tarea que se le asignó.
## **CONEXIÓN A LA BASE DE DATOS**
Hemos realizado la conexión a la base de datos utilizando el fichero application.propertis, que contiene la información necesaria para configurar esta conexión.

![Texto alternativo](https://files.catbox.moe/v1z0vp.png)

## **ENTIDADES**
Una vez configurada la conexión con la base de datos, las entidades han sido generadas automáticamente con anotaciones JPA. Estas entidades contienen solamente los atributos de cada tabla y los métodos getters y setters. Para las relaciones entre tablas hemos utilizado listas y objetos apropiadamente.

![Texto alternativo](https://files.catbox.moe/c7cga1.png)

![Texto alternativo](https://files.catbox.moe/dr0mb0.png)

![Texto alternativo](https://files.catbox.moe/pucpho.png)

![Texto alternativo](https://files.catbox.moe/t96kh0.png)

![Texto alternativo](https://files.catbox.moe/r80fkj.png)


## **REPOSITORIOS**
Los repositorios son interfaces vacías que extienden de la interfaz CrudRepository proporcionada por Spring Data, que incluye métodos para operaciones CRUD (Crear, Leer, Borrar y Actualizar). Es decir estos repositorios contienen métodos predefinidos que nos facilitan el trabajo a la hora de trabajar con bases de datos.

![Texto alternativo](https://files.catbox.moe/3e6lt1.png)

![Texto alternativo](https://files.catbox.moe/fuiph5.png)

![Texto alternativo](https://files.catbox.moe/iosxhj.png)

![Texto alternativo](https://files.catbox.moe/yp40ql.png)

![Texto alternativo](https://files.catbox.moe/xd6n25.png)

## **CONTROLADORES**
Los controladores son las clases encargadas de manejar las GET, PUT, POST y DELETE. Es decir, el controlador accede al repositorio correspondiente a esa tabla para realizar las distintas operaciones sobre la base de datos. Además los controladores guardan las acciones realizadas en la tabla histórico y en un archivo.

![Texto alternativo](https://files.catbox.moe/ebt7c9.png)

![Texto alternativo](https://files.catbox.moe/5gxtbj.png)

![Texto alternativo](https://files.catbox.moe/rhzlyq.png)

![Texto alternativo](https://files.catbox.moe/rg1clk.png)

## **INTERFAZ**
La interfaz consiste en una pantalla destinada a realizar el inicio de sesión en la aplicación, junto con otras pantallas diseñadas para llevar a cabo diversas operaciones de forma interactiva sobre la base de datos.

### **LOGIN**

![Texto alternativo](https://files.catbox.moe/2b3mtk.png)

![Texto alternativo](https://files.catbox.moe/84j9zj.png)

### **PANTALLA PRINCIPAL**
Esta pantalla solamente contiene el menú de navegación para moverse por las distintas tablas y un botón para cerrar sesión.

![Texto alternativo](https://files.catbox.moe/wjics4.png)

![Texto alternativo](https://files.catbox.moe/usvi0j.png)

### **PANTALLAS DE LECTURA**
Estas pantallas contienen la información de las distintas tablas, préstamos, libros…

![Texto alternativo](https://files.catbox.moe/zhgouw.png)

![Texto alternativo](https://files.catbox.moe/93wrw4.png)

![Texto alternativo](https://files.catbox.moe/bkziv3.png)

![Texto alternativo](https://files.catbox.moe/1xy8jz.png)

![Texto alternativo](https://files.catbox.moe/nx8r8x.png)

### **FORMULARIOS DE INSERCIÓN Y ACTUALIZACIÓN**
Formularios para insertar y modificar los datos de una fila en la base de datos.

![Texto alternativo](https://files.catbox.moe/p5d0aj.png)

![Texto alternativo](https://files.catbox.moe/953p3o.png)

![Texto alternativo](https://files.catbox.moe/0i88rn.png)

![Texto alternativo](https://files.catbox.moe/btv8ub.png)

![Texto alternativo](https://files.catbox.moe/r9nll9.png)

![Texto alternativo](https://files.catbox.moe/p6erbw.png)

### **MÉTODOS CRUD**
Métodos utilizados para realizar las peticiones al servicio de API REST al realizar las operaciones CRUD. Cada tabla tiene unos métodos propios para insertar, borrar y actualizar. Este es un ejemplo, de los métodos utilizados para las operaciones con la tabla Libro.

![Texto alternativo](https://files.catbox.moe/crmq1c.png)

![Texto alternativo](https://files.catbox.moe/esiijo.png)

![Texto alternativo](https://files.catbox.moe/s2bqc0.png)

# **CONCLUSIÓN**
En conclusión, este proyecto nos ha ayudado a conocer y explorar las facilidades que nos aporta SpringBoot, haciendo que realizar operaciones sobre bases de datos sea mucho más facil y rápido. Además hemos adquirido bastantes conocimientos acerca de PHP al hacer la interfaz y al utilizar en ella el código Java realizado en el backend. Por último, hemos aprendido algunas cosas sobre metodología ágiles y de como trabajar en grupo de forma correcta y equitativa.
