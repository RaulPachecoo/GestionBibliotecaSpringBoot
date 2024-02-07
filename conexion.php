<?php 

/*Aqui me conecto a mi base de datos, para saber el host, el usuario y la contraseña lo podemos ver en php myadmin*/
$host = "localhost";
$usuario = "root";
$contraseña ="";
$bd = "accesoDatos";
$dsn = "mysql:host=$host;dbname=$bd";

try{/*Establece la conexion dentro de este try/catch */
    $conexion = new PDO($dsn, $usuario, $contraseña);
  
}catch(PDOException $error){
    echo $error->getMessage();/*Si falla mostrará un mensaje de error */
    
}