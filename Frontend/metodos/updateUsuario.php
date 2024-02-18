<?php
session_start();

// Verificar si se recibió el ID del usuario y los datos del usuario desde el formulario
if(isset($_POST['id']) && isset($_POST['nombre']) && isset($_POST['apellidos'])){
    $id = $_POST['id']; // Obtener el ID del usuario desde el formulario
    $nombre = $_POST['nombre'];
    $apellidos = $_POST['apellidos'];

    // Datos a enviar en el cuerpo de la solicitud PUT
    $datos = array(
        "nombre" => $nombre,
        "apellidos" => $apellidos
    );
    $datos_string = json_encode($datos);

    // URL del endpoint para actualizar usuarios
    $url = 'http://localhost:8080/BIBLIOTECA/usuario/' . $id;

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
        echo 'Usuario actualizado correctamente';
    }

    // Cerrar la sesión cURL
    curl_close($ch);

    // Redireccionar después de actualizar el usuario
    header("Location: ../Tablas/usuario.php");
    exit();
} else {
    echo "No se recibió el ID del usuario y/o los datos del usuario desde el formulario";
    // Manejar el caso en que no se recibió el ID del usuario y/o los datos del usuario desde el formulario
}
?>
