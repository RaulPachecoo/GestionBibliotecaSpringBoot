<?php
if(isset($_GET['id'])){
    $idLibro = $_GET['id']; // Cambiado de $idPrestamo a $idLibro
    $tipo = 'libro'; // Tipo de recurso a eliminar

    // URL del endpoint para eliminar préstamos
    $url = 'http://localhost:8080/BIBLIOTECA/' . $tipo . '/' . $idLibro;

    // Inicializar cURL
    $ch = curl_init($url);

    // Configurar la solicitud cURL
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    // Ejecutar la solicitud cURL y obtener la respuesta
    $response = curl_exec($ch);

    // Verificar si hubo errores
    if ($response === false) {
        echo 'Error de cURL: ' . curl_error($ch);
    } else {
        echo 'Libro eliminado correctamente';
    }

    // Cerrar la sesión cURL
    curl_close($ch);

    // Redireccionar después de eliminar el préstamo
    header("Location: ../Tablas/libro.php");
    exit();
} else {
    echo "No se recibió el ID del libro a eliminar";
    // Manejar el caso en que no se recibe el ID del préstamo
}
?>