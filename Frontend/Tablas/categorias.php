<!DOCTYPE html>
<html>
  <head>
    <title>Categorias</title>

    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">





<?php
$url = "http://localhost:8080/BIBLIOTECA/entidadCategorias";
$json = file_get_contents($url);
$obj = json_decode($json);//Convierte un string codificado en JSON a una variable de PHP.


// Acceder a las categorías y lo guardamos en una variable
$categorias = $obj->_embedded->entidadCategorias;
?>
<div class="col text-center">
<table class="table table-striped">
<thead>
  <tr>
    <th scope="col">Categorías</th>
  </tr>
  </thead>
  <tbody>
  <?php foreach ($categorias as $categoria): ?>
  <tr>
    <td><?php echo $categoria->categoria; ?></td>


  </tr>
  <?php endforeach; ?>
</table>
  </div>

</head>
</html>
