<?php
session_start();

if(isset($_GET['id']) && isset($_GET['tipo'])){
    $id = $_GET['id'];
    $tipo = $_GET['tipo'];
    // URL del endpoint para eliminar usuarios
    $url = 'http://localhost:8080/BIBLIOTECA/' . $tipo . '/' . $id; // Corrige la concatenación del id

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
        echo 'Usuario eliminado correctamente';
    }

    // Cerrar la sesión cURL
    curl_close($ch);

    // Redireccionar después de eliminar el usuario
    header("Location: ../Tablas/usuario.php");
    exit();
} else {
    echo "No se encontró un id de sesión";
    // Manejar el caso en que no hay una sesión válida o un id de sesión
}
?>
