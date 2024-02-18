<?php
session_start();

// Verificar si se recibió el ID del usuario desde la URL
if(isset($_GET['id'])){
    $id = $_GET['id']; // Obtener el ID del usuario de la URL
    $_SESSION['id'] = $id; // Almacenar el ID del usuario en la sesión
} else {
    echo "No se recibió el ID del usuario desde la URL";
    exit();
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            background-image: url(../imagenes/fondo_difuminado_login.jpg);
            background-position: center;
            background-size: cover;
        }
        .container {
            margin-top: 50px;
            width: 510px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0px 0px 5px 5px rgba(0,0,0,0.15);
            padding: 30px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Formulario de Usuario</h1>
    <br>
    <form action="../metodos/updateUsuario.php" method="post">
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <!-- Utilizar un input de tipo text para ingresar el nombre del usuario -->
            <input type="text" class="form-control" id="nombre" name="nombre">
        </div>
        <div class="form-group">
            <label for="apellidos">Apellidos</label>
            <!-- Utilizar un input de tipo text para ingresar los apellidos del usuario -->
            <input type="text" class="form-control" id="apellidos" name="apellidos">
        </div>
        <br>
        <!-- Agregar un campo oculto para enviar el ID del usuario -->
        <input type="hidden" name="id" value="<?php echo $id ?>">
        <!-- Botón para enviar el formulario -->
        <button type="submit" class="btn btn-primary">Actualizar Usuario</button>
        <br>
    </form>
</div>
</body>
</html>
