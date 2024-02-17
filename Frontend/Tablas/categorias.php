<?php
require_once('../navBar.php');

?>



<!DOCTYPE html>
<html>
<head>
    <title>Categorias</title>

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
    </style>
</head>
<body>

<?php

$url = "http://localhost:8080/BIBLIOTECA/categoria";
$json = file_get_contents($url);
$obj = json_decode($json);//Convierte un string codificado en JSON a una variable de PHP.

//var_dump($obj);

// Acceder a las categorías y lo guardamos en una variable
//$ids = $obj[0] ->id;
//var_dump($ids) ;
?>
<h1>Categorias</h1><hr>
<div class="contenedor_tabla" >


    <table class="table table-striped"  >
        <thead>
        <tr>
            <th scope="col" >Id</th>
            <th scope="col"  >Nombre</th>
        </tr>
        </thead>
        <tbody>
        <?php foreach ($obj as $ob): ?>
            <tr>
                <td><?php echo $ob -> id; ?></td>
                <td><?php echo $ob -> categoria; ?></td>

            </tr>
        <?php endforeach; ?>
    </table>
</div>

</body>
</html>