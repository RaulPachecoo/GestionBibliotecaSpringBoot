<?php
global $conexion;
session_start();
require "conexion.php";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $usuario = $_POST["usuario"];
    $password = $_POST["password"];
    $_SESSION['usuario'] = $usuario;
    $_SESSION['password'] = $password;
    //$error = "";
    
    // Utilizar una consulta preparada para evitar la inyección de SQL
    $sql = "SELECT * FROM accesoDatos WHERE usuario = ? AND password = ?";
    $stmt = $conexion->prepare($sql);
    $stmt->execute([$usuario, $password]);

    // Verificar si se encontraron resultados
    if ($stmt->rowCount() > 0) {
        header("Location: ./pPrincipal.php");
        exit; // Salir del script después de redireccionar
    } else {
        echo "<script>alert('Error: Usuario o contraseña incorrectos');</script>";
        //$error = "Usuario o contraseña incorrectos";
    }
}
?>

<!-- Define que el documento esta bajo el estandar de HTML 5 -->
<!doctype html>

<!-- Representa la raíz de un documento HTML o XHTML. Todos los demás elementos deben ser descendientes de este elemento. -->
<html lang="es">
    
    <head>
        
        <meta charset="utf-8">
        
        <title> Login </title>    
        
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        
        
        <link href="https://fonts.googleapis.com/css?family=Nunito&display=swap" rel="stylesheet"> 
        
        <!-- Link hacia el archivo de estilos css -->
        <link rel="stylesheet" href="css/login.css">
        
        <style type="text/css">
            
        </style>
        
        <script type="text/javascript">
        
        </script>
        
    </head>
    
    <body>
    
        <div id="contenedor">
            
            <div id="contenedorcentrado">
                <div id="login">
                    <form id="loginform" method="POST" action="index.php">
                        <label for="usuario">Usuario</label>
                        <input id="usuario" type="text" name="usuario" placeholder="Usuario" required>
                        
                        <label for="password">Contraseña</label>
                        <input id="password" type="password" placeholder="Contraseña" name="password" required>
                        
                        <button type="submit" title="Ingresar" name="Ingresar">Login</button>
                        
                    </form>
                  
                    
                </div>
                
                <div id="derecho">
                    <div class="titulo">
                        Bienvenido
                    </div>
                    <hr>
                    <div class="pie-form">
                        <p>¿No tienes Cuenta?</p>
                        <a href="./registro.php">Registrate</a>
                        <hr>
                        
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>


