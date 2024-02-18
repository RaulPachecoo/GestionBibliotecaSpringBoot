<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Préstamo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        body{
            display: flex;
            align-items: center;
            justify-content: center;
            background-image: url(../imagenes/fondo_difuminado_login.jpg);
            background-position: center;
            background-size: cover;
        }
        .container{
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
        <h1>Formulario de Edición de Préstamo</h1>
        <br>
        <form action="../metodos/updatePrestamo.php" method="post">
            <?php
            // Obtener el ID del préstamo de la URL
            $idPrestamo = $_GET['id'];

            // URL del endpoint para obtener los detalles del préstamo por ID
            $url = "http://localhost:8080/BIBLIOTECA/prestamos/$idPrestamo";
            $json = file_get_contents($url);
            $prestamo = json_decode($json);

            // Verificar si se obtuvieron los detalles del préstamo
            if ($prestamo) {
                // Obtener la fecha de préstamo, el ID del libro y el ID del usuario del préstamo actual
                $fechaPrestamo = $prestamo->fechaPrestamo;
                $idLibro = $prestamo->libro->id;
                $idUsuario = $prestamo->usuario->id;
            ?>
            <!-- Campo oculto para enviar el ID del préstamo -->
            <input type="hidden" name="idPrestamo" value="<?php echo $idPrestamo; ?>">

            <div class="form-group">
                <label for="fechaPrestamo">Fecha de Préstamo</label>
                <input type="date" class="form-control" id="fechaPrestamo" name="fechaPrestamo" value="<?php echo $fechaPrestamo; ?>">
            </div>
            <label for="libro">Libro</label>
            <select class="form-control" id="libro" name="libro">
                <!-- Seleccionar el libro del préstamo -->
                <?php
                $url = "http://localhost:8080/BIBLIOTECA/libro";
                $json = file_get_contents($url);
                $libros = json_decode($json);
                foreach ($libros as $libro): 
                    $selected = ($libro->id == $idLibro) ? 'selected' : '';
                    echo "<option value='" . $libro->id . "' $selected>" . $libro->nombre . "</option>";
                endforeach;
                ?>
            </select>
            <label for="usuario">Usuario</label>
            <select class="form-control" id="usuario" name="usuario">
                <!-- Seleccionar el usuario del préstamo -->
                <?php
                $url = "http://localhost:8080/BIBLIOTECA/usuario";
                $json = file_get_contents($url);
                $usuarios = json_decode($json);
                foreach ($usuarios as $usuario): 
                    $selected = ($usuario->id == $idUsuario) ? 'selected' : '';
                    echo "<option value='" . $usuario->id . "' $selected>" . $usuario->nombre . "</option>";
                endforeach;
                ?>
            </select>
            <?php } ?>
            <br>
            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
            <br>
        </form>
    </div>
</body>
</html>
