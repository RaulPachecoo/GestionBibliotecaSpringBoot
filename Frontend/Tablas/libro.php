<?php
require_once('../navBar.php');

?>



<!DOCTYPE html>
<html>
  <head>
    <title>Libros</title>

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
  <br>
<a href="../pPrincipal.php"><button class="btn btn-primary">Atrás</button></a>
<?php

$url = "http://localhost:8080/BIBLIOTECA/libro";
$json = file_get_contents($url);
$obj = json_decode($json);//Convierte un string codificado en JSON a una variable de PHP.



//var_dump($obj);

// Acceder a los libros y lo guardamos en una variable
//$ids = $obj[0] ->id;
//var_dump($ids) ;
?>
 <h1>Libros</h1><hr>
<div class="contenedor_tabla" >
 

<table class="table table-striped"  >
<thead>
  <tr>
    <th scope="col" >Id</th>
    <th scope="col"  >Nombre</th>
    <th scope="col" >Autor</th>
    <th scope="col"  >editorial</th>
    <th scope="col"  >categoria</th>
    <th scope="col" > </th>
  </tr>
  </thead>
  <tbody>
  <?php foreach ($obj as $ob): ?>
  <tr>
    <td><?php echo $ob -> id; ?></td>
    <td><?php echo $ob -> nombre; ?></td>
    <td><?php echo $ob -> autor; ?></td>
    <td><?php echo $ob -> editorial; ?></td>
    <!--Importante aqui,el valor de categoria en los libros es el objeto categoria como tal,asi que le sacamos la categoria a categoria -->
    <td><?php echo $ob->categoria->categoria; ?></td>

    <td><a href="../formularios/updateFormLibro.php"><button><img src="https://cdn-icons-png.flaticon.com/256/45/45706.png" class="icono"/>
        <?php $_SESSION["id"] = $ob -> id; $_SESSION["libro"]= $ob -> categoria;?></button></a><a href="../metodos/deleteLibro.php">
            <button><img src="https://cdn-icons-png.flaticon.com/256/4265/4265064.png" class="icono"/>
        <?php $_SESSION["id"] = $ob -> id; $_SESSION["tipo"]= "libro"?></button></a> </td>

  </tr>
  <?php endforeach; ?>
</table>
 <a href="../formularios/addFormLibro.php"><button class="btn btn-primary">Añadir</button></a>
  </div>

  </body>
</html>