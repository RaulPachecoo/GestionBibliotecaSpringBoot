<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Añadir Préstamo</title>
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
        <h1>Formulario de Préstamo</h1>
        <br>
        <form action="../metodos/addPrestamo.php" method="post">
            <div class="form-group">
                <label for="fechaPrestamo">Fecha de Préstamo</label>
                <input type="date" class="form-control" id="fechaPrestamo" name="fechaPrestamo">
            </div>
            <label for="libro">Libro</label>
            <select class="form-control" id="libro" name="libro"> <!-- Seleccionando el ID del libro -->
                <?php
                $url = "http://localhost:8080/BIBLIOTECA/libro";
                $json = file_get_contents($url);
                $obj = json_decode($json);
                foreach ($obj as $ob): 
                    echo "<option value='" . $ob->id . "'>" . $ob->nombre . "</option>";
                endforeach;
                ?>
            </select>
            <label for="usuario">Usuario</label>
            <select class="form-control" id="usuario" name="usuario"> <!-- Seleccionando el ID del usuario -->
                <?php
                $url = "http://localhost:8080/BIBLIOTECA/usuario";
                $json = file_get_contents($url);
                $obj = json_decode($json);
                foreach ($obj as $ob): 
                    echo "<option value='" . $ob->id . "'>" . $ob->nombre . "</option>";
                endforeach;
                ?>
            </select>
            <br>
            <button type="submit" class="btn btn-primary">Enviar</button>
            <br>
        </form>

    </div>
</body>
</html>
