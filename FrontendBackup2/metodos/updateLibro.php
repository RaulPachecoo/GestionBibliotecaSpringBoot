<?php
session_start();

// Verificar si se recibió el ID del libro y los datos del libro desde el formulario
if(isset($_SESSION['id']) && isset($_POST['nombre']) && isset($_POST['autor']) && isset($_POST['editorial']) && isset($_POST['categoria'])){
    $id = $_SESSION['id'];
    $nombre = $_POST['nombre'];
    $autor = $_POST['autor'];
    $editorial = $_POST['editorial'];
    $categoria = $_POST['categoria'];

    // Datos a enviar en el cuerpo de la solicitud PUT
    $datos = array(
        "nombre" => $nombre,
        "autor" => $autor,
        "editorial" => $editorial,
        "categoria" => $categoria
    );
    $datos_string = json_encode($datos);

    // URL del endpoint para actualizar libros
    $url = 'http://localhost:8080/BIBLIOTECA/libro/' . $id;

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
        echo 'Libro actualizado correctamente';
    }

    // Cerrar la sesión cURL
    curl_close($ch);

    // Redireccionar después de actualizar el libro
    header("Location: ../Tablas/libro.php");
    exit();
} else {
    echo "No se recibió el ID del libro y/o los datos del libro desde el formulario";
    // Manejar el caso en que no se recibió el ID del libro y/o los datos del libro desde el formulario
}
?>