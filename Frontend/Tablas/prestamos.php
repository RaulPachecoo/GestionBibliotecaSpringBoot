<!DOCTYPE html>
<html>
<head>
    <title>Préstamos</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        table {
            border: solid 1px black;
        }
        .contenedor_tabla {
            width: 400px;
        }
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
        }
        .icono {
            width: 25px;
            height: 25px;
        }
    </style>
</head>
<body>
<br>
<a href="../pPrincipal.php"><button class="btn btn-primary">Atrás</button></a>

<?php
$url = "http://localhost:8080/BIBLIOTECA/prestamos";
$json = file_get_contents($url);
$obj = json_decode($json);
?>

<h1>Préstamos</h1><hr>

<div class="contenedor_tabla">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Fecha de Préstamo</th>
            <th scope="col">Libro</th>
            <th scope="col">Usuario</th>
            <th scope="col">Acciones</th>
        </tr>
        </thead>
        <tbody>
        <?php foreach ($obj as $ob): ?>
            <tr>
                <td><?php echo $ob -> idPrestamo; ?></td>
                <!-- Formatear la fecha en el formato deseado -->
                <td><?php echo date('d/m/Y', strtotime($ob -> fechaPrestamo)); ?></td>
                <td><?php echo $ob->libro->nombre; ?></td>
                <td><?php echo $ob->usuario->nombre; ?></td>
                <td>
                    <a href="../formularios/updateFormPrestamo.php?id=<?php echo $ob->idPrestamo; ?>">
                        <button><img src="https://cdn-icons-png.flaticon.com/256/45/45706.png" class="icono"/></button>
                    </a>
                    <a href="../metodos/deletePrestamo.php?id=<?php echo $ob->idPrestamo; ?>">
                        <button><img src="https://cdn-icons-png.flaticon.com/256/4265/4265064.png" class="icono"/></button>
                    </a>
                </td>

            </tr>
        <?php endforeach; ?>
        </tbody>
    </table>
    <a href="../formularios/addFormPrestamo.php"><button class="btn btn-primary">Añadir Préstamo</button></a>
</div>
</body>
</html>
