<?php
session_start();

// Verificar si se recibió el nombre de la categoría desde el formulario
if(isset($_POST['categoria'])){
    echo 'ha entrado';
    $categoria = $_POST['categoria'];
    echo $categoria;
    // Datos a enviar en el cuerpo de la solicitud POST
    $datos = array("categoria" => $categoria);
    $datos_string = json_encode($datos);

    // URL del endpoint para crear categorías
    $url = 'http://localhost:8080/BIBLIOTECA/categoria';

    // Inicializar cURL
    $ch = curl_init($url);

    // Configurar la solicitud cURL
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $datos_string);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Ejecutar la solicitud cURL y obtener la respuesta
    $response = curl_exec($ch);

    // Verificar si hubo errores
    if ($response === false) {
        echo 'Error de cURL: ' . curl_error($ch);
    } else {
        echo 'Categoría creada correctamente';
    }

    // Cerrar la sesión cURL
    curl_close($ch);

    // Redireccionar después de crear la categoría
   header("Location: ../Tablas/categorias.php");
    exit();
} else {
    echo "No se recibió el nombre de la categoría";
    // Manejar el caso en que no se recibió el nombre de la categoría
}
?>