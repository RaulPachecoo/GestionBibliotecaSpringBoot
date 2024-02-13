<?php
$url = "http://localhost:8080/BIBLIOTECA/entidadCategorias";
$json = file_get_contents($url);
$obj = json_decode($json);//Convierte un string codificado en JSON a una variable de PHP.


// Acceder a las categorías y lo guardamos en una variable
$categorias = $obj->_embedded->entidadCategorias;
?>

<table border= 1>
  <tr>
    <th>Categorías</th>
  </tr>
  <?php foreach ($categorias as $categoria): ?>
  <tr>
    <td><?php echo $categoria->categoria; ?></td>
 

  </tr>
  <?php endforeach; ?>
</table>
