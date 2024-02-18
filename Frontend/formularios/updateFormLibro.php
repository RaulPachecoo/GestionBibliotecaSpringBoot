<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Libro</title>
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
        <h1>Formulario de Edición de Libro</h1>
        <br>
        <form action="../metodos/updateLibro.php" method="post">
            <?php
            // Obtener el ID del libro de la URL
            $idLibro = $_GET['id'];

            // URL del endpoint para obtener los detalles del libro por ID
            $url = "http://localhost:8080/BIBLIOTECA/libro/$idLibro";
            $json = file_get_contents($url);
            $libro = json_decode($json);

            // Verificar si se obtuvieron los detalles del libro
            if ($libro) {
                // Obtener los detalles del libro
                $nombre = $libro->nombre;
                $autor = $libro->autor;
                $editorial = $libro->editorial;
                $idCategoria = $libro->categoria->id;
            ?>
            <!-- Campo oculto para enviar el ID del libro -->
            <input type="hidden" name="idLibro" value="<?php echo $idLibro; ?>">

            <div class="form-group">
                <label for="nombre">Nombre</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="<?php echo $nombre; ?>">
            </div>
            <div class="form-group">
                <label for="autor">Autor</label>
                <input type="text" class="form-control" id="autor" name="autor" value="<?php echo $autor; ?>">
            </div>
            <div class="form-group">
                <label for="editorial">Editorial</label>
                <input type="text" class="form-control" id="editorial" name="editorial" value="<?php echo $editorial; ?>">
            </div>
            <label for="categoria">Categoría</label>
            <select class="form-control" id="categoria" name="categoria">
                <!-- Seleccionar la categoría del libro -->
                <?php
                $url = "http://localhost:8080/BIBLIOTECA/categoria";
                $json = file_get_contents($url);
                $categorias = json_decode($json);
                foreach ($categorias as $categoria): 
                    $selected = ($categoria->id == $idCategoria) ? 'selected' : '';
                    echo "<option value='" . $categoria->id . "' $selected>" . $categoria->categoria . "</option>";
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
