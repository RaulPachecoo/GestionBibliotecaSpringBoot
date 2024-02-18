<?php
session_start();

// Verificar si se recibió el ID de la categoría y el nombre de la categoría desde el formulario
if(isset($_SESSION['id']) && isset($_POST['categoria'])){
    $id = $_SESSION['id'];
    $categoria = $_POST['categoria'];
    //echo " $id $categoria ";

    // Datos a enviar en el cuerpo de la solicitud PUT
    $datos = array("categoria" => $categoria);
    $datos_string = json_encode($datos);

    // URL del endpoint para actualizar categorías
    $url = 'http://localhost:8080/BIBLIOTECA/categoria/' . $id;

    // Inicializar cURL
    $ch = curl_init($url);

    // Configurar la solicitud cURL
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PUT");
    curl_setopt($ch, CURLOPT_POSTFIELDS, $datos_string);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Ejecutar la solicitud cURL y obtener la respuesta
    $response = curl_exec($ch);

    // Verificar si hubo errores
    if ($response === false) {
        echo 'Error de cURL: ' . curl_error($ch);
    } else {
        echo 'Categoría actualizada correctamente';
    }

    // Cerrar la sesión cURL
    curl_close($ch);

    // Redireccionar después de actualizar la categoría
    header("Location: ../Tablas/categorias.php");
    exit();
} else {
    echo "No se recibió el ID de la categoría y/o el nombre de la categoría desde el formulario";
    // Manejar el caso en que no se recibió el ID de la categoría y/o el nombre de la categoría desde el formulario
}
?>
