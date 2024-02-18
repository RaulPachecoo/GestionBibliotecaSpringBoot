<?php 

$host = "localhost";
$usuario = "root";
$contraseña ="root";
$bd = "accesoDatos";
$dsn = "mysql:host=$host;dbname=$bd";

try {
    /* Establecer la conexión utilizando PDO */
    $conexion = new PDO($dsn, $usuario, $contraseña);
    /* Establecer el modo de errores de PDO a excepciones */
    $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch(PDOException $error) {
    /* Mostrar mensaje de error si la conexión falla */
    echo "Error de conexión: " . $error->getMessage();
}
