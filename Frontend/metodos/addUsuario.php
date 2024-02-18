<?php
session_start();

// Verificar si se recibieron los datos del usuario desde el formulario
if(isset($_POST['id']) && isset($_POST['nombre']) && isset($_POST['apellidos'])){
    // Obtener los datos del usuario desde el formulario
    $id = $_POST['id'];
    $nombre = $_POST['nombre'];
    $apellidos = $_POST['apellidos'];

    // Datos a enviar en el cuerpo de la solicitud POST
    $datos = array(
        "id" => $id,
        "nombre" => $nombre,
        "apellidos" => $apellidos
    );
    $datos_string = json_encode($datos);

    // URL del endpoint para crear usuarios
    $url = 'http://localhost:8080/BIBLIOTECA/usuario';

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
        echo 'Usuario creado correctamente';
    }

    // Cerrar la sesión cURL
    curl_close($ch);

    // Redireccionar después de crear el usuario
    header("Location: ../Tablas/usuario.php");
    exit();
} else {
    echo "No se recibieron todos los datos del usuario";
    // Manejar el caso en que no se recibieron todos los datos del usuario
}

?>
