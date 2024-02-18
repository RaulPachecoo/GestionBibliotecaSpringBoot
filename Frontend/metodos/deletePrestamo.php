<?php
session_start();

// Verificar si se recibió el ID del préstamo a eliminar
if(isset($_GET['id'])){
    $idPrestamo = $_GET['id'];
    $tipo = 'prestamos'; // Tipo de recurso a eliminar

    // URL del endpoint para eliminar préstamos
    $url = 'http://localhost:8080/BIBLIOTECA/' . $tipo . '/' . $idPrestamo;

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
        echo 'Préstamo eliminado correctamente';
    }

    // Cerrar la sesión cURL
    curl_close($ch);

    // Redireccionar después de eliminar el préstamo
    header("Location: ../Tablas/prestamos.php");
    exit();
} else {
    echo "No se recibió el ID del préstamo a eliminar";
    // Manejar el caso en que no se recibe el ID del préstamo
}
?>
