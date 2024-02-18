<?php
require_once('../navBar.php');

?>

<!DOCTYPE html>
<html>
<head>
    <title>Usuarios</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        table{
            border:  solid 1px black;
        }

        .contenedor_tabla{
            width: 400px;
        }
        body{
            display:flex;
            align-items:center;
            justify-content:center;
            flex-direction: column;
        }
        .icono{
            width: 25px;
            height: 25px;
        }
    </style>
</head>
<body>

<?php

$url = "http://localhost:8080/BIBLIOTECA/usuario";
$json = file_get_contents($url);
$obj = json_decode($json);//Convierte un string codificado en JSON a una variable de PHP.


?>
<h1>Usuarios</h1><hr>
<div class="contenedor_tabla" >


    <table class="table table-striped"  >
        <thead>
        <tr>
            <th scope="col" >Id</th>
            <th scope="col"  >Nombre</th>
            <th scope="col" >Apellidos</th>
        </tr>
        </thead>
        <tbody>
        <?php foreach ($obj as $ob): ?>
            <tr>
                <td><?php echo $ob -> id; ?></td>
                <td><?php echo $ob -> nombre; ?></td>
                <td><?php echo $ob -> apellidos; ?></td>

                <td>
                    <a href="../formularios/updateFormUsuario.php?id=<?php echo $ob->id; ?>">
                        <button>
                            <img src="https://cdn-icons-png.flaticon.com/256/45/45706.png" class="icono"/>
                        </button>
                    </a>
                    <a href="../metodos/deleteUsuario.php?id=<?php echo $ob->id; ?>&tipo=usuario">
                        <button>
                            <img src="https://cdn-icons-png.flaticon.com/256/4265/4265064.png" class="icono"/>
                        </button>
                    </a>
                </td>

            </tr>
        <?php endforeach; ?>
    </table>
    <a href="../formularios/addFormUsuario.php"><button class="btn btn-primary">Añadir</button></a>
</div>

</body>
</html>
