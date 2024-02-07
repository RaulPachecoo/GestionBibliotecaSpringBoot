<?php
session_start();
require "conexion.php";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $usuario = $_POST["usuario"];
    $password = $_POST["password"];
    $_SESSION['usuario'] = $usuario;
    $_SESSION['password'] = $password;

    $conexion = mysqli_connect("localhost", "root", "", "accesoDatos");

    if (!$conexion) {
        die("Error de conexión: " . mysqli_connect_error());
    }

    // Utilizar una consulta preparada para evitar la inyección de SQL
    $sql = "INSERT INTO accesoDatos (usuario, password) VALUES (?, ?)";
    $stmt = mysqli_prepare($conexion, $sql);

    if ($stmt) {
        // Liga los parámetros y ejecuta la sentencia preparada
        mysqli_stmt_bind_param($stmt, "ss", $usuario, $password);
        $resultado = mysqli_stmt_execute($stmt);

        if ($resultado) {
            echo "Usuario registrado correctamente";
            header("Location: ./realizad.php");
            // Puedes redirigir a la página principal u otra página después del registro
        } else {
            echo "Error en el registro";
        }

        // Cierra la sentencia preparada
        mysqli_stmt_close($stmt);
    } else {
        echo "Error en la preparación de la sentencia";
    }

    mysqli_close($conexion);
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="Videojuegos & Desarrollo">
    <meta name="description" content="Ejemplo de formulario de acceso basado en HTML5 y CSS">
    <meta name="keywords" content="login,formulariode acceso html">
    <link href="https://fonts.googleapis.com/css?family=Nunito&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<div id="contenedor">
    <div id="contenedorcentrado">
        <div id="login">
            <form id="loginform" method="post" action="">
                <label for="usuario">Usuario</label>
                <input id="usuario" type="text" name="usuario" placeholder="Usuario" required>
                
                <label for="password">Contraseña</label>
                <input id="password" type="password" placeholder="Contraseña" name="password" required>
                
                <button type="submit" title="Ingresar" name="Ingresar">Registro</button>
            </form>
        </div>
        <div id="derecho">
            <div class="titulo">
                Bienvenido
            </div>
            <hr>
            <div class="pie-form">
                <a href="login.php">« Volver</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
