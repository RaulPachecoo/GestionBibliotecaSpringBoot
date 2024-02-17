<?php
session_start();
$nombre = isset($_SESSION['usuario']) ? $_SESSION['usuario'] : "hola";


?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Has entrado correctamente</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .navbar {
            background-color: #007bff; /* Color azul */
        }

        .navbar-brand {
            color: #fff !important; /* Color blanco */
        }
    </style>
</head>
<body>
<div class="container">

    <nav class="navbar navbar-expand-lg navbar-dark bg-primary mt-3"> <!-- navbar-dark para que el texto sea blanco -->
        <a class="navbar-brand">Bienvenido <?php echo $nombre; ?></a> <!-- Reemplaza [nombre] con el nombre del usuario -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link active" href="./Tablas/categorias.php">Categoria </a>
                <a class="nav-item nav-link active" href="#">Libro </a>
                <a class="nav-item nav-link active" href="#">Prestamo </a>
                <a class="nav-item nav-link active" href="#">Usuario </a>

            </div>
        </div>
    </nav>


</div>

<!-- Bootstrap JS y dependencias opcionales -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
