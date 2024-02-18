<?php
session_start();

if(isset($_POST['nombre']) && isset($_POST['autor']) && isset($_POST['editorial']) && isset($_POST['categoria'])){
    // Recuperar los datos del formulario
    $nombre = $_POST['nombre'];
    $autor = $_POST['autor'];
    $editorial = $_POST['editorial'];
    $idCategoria = $_POST['categoria'];

    // Obtener el objeto categoria
    $objetoCategoria = obtenerObjetoCategoria($idCategoria);

    if ($objetoCategoria=== null) {
        echo "No se encontró la categoria en la base de datos";
        exit();
    }

    // Datos del préstamo a enviar en la solicitud POST
    $datos = array(
        "nombre" => $nombre,
        "autor" => $autor,
        "editorial" => $editorial,
        "categoria" => $objetoCategoria
    );
    $datos_string = json_encode($datos);

    // URL del endpoint para crear préstamos
    $url = 'http://localhost:8080/BIBLIOTECA/libro';

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
        echo 'Libro creado correctamente';
    }

    // Cerrar la sesión cURL
    curl_close($ch);

    // Redireccionar después de crear el préstamo
    header("Location: ../Tablas/libro.php");
    exit();
} else {
    echo "No se recibieron todos los datos necesarios para crear el libro";
}


// Función para obtener el objeto categoría a partir del ID
function obtenerObjetoCategoria($idCategoria) {
    // URL del endpoint para obtener el objeto categoría por ID
    $url = "http://localhost:8080/BIBLIOTECA/categoria/$idCategoria";

    // Realizar la solicitud al endpoint
    $json = file_get_contents($url);

    // Decodificar la respuesta JSON
    $objetoCategoria = json_decode($json);

    // Verificar si se encontró la categoría
    if ($objetoCategoria === null) {
        return null;
    }

    return $objetoCategoria;
}

?>