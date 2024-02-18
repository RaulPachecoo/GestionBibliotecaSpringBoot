<?php
session_start();

if(isset($_POST['fechaPrestamo']) && isset($_POST['libro']) && isset($_POST['usuario']) && isset($_POST['idPrestamo'])){
    // Recuperar los datos del formulario
    $fechaPrestamo = $_POST['fechaPrestamo'];
    $idLibro =  $_POST['libro']; // Obtener el ID del libro seleccionado
    $idUsuario = $_POST['usuario']; // Obtener el ID del usuario seleccionado
    $idPrestamo = $_POST['idPrestamo']; // Obtener el ID del préstamo a actualizar

    // Obtener el objeto libro y usuario
    $objetoLibro = obtenerObjetoLibro($idLibro);
    $objetoUsuario = obtenerObjetoUsuario($idUsuario);

    if ($objetoLibro === null) {
        echo "No se encontró el libro en la base de datos";
        exit();
    }

    if ($objetoUsuario === null) {
        echo "No se encontró el usuario en la base de datos";
        exit();
    }

    // Datos del préstamo a enviar en la solicitud PUT
    $datos = array(
        "fechaPrestamo" => $fechaPrestamo,
        "libro" => $objetoLibro,
        "usuario" => $objetoUsuario
    );
    $datos_string = json_encode($datos);

    // URL del endpoint para actualizar préstamos
    $url = 'http://localhost:8080/BIBLIOTECA/prestamos/' . $idPrestamo;

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
        echo 'Préstamo actualizado correctamente';
    }

    // Cerrar la sesión cURL
    curl_close($ch);

    // Redireccionar después de actualizar el préstamo
    header("Location: ../Tablas/prestamos.php");
    exit();
} else {
    echo "No se recibieron todos los datos necesarios para actualizar el préstamo";
}

// Función para obtener el objeto libro a partir del ID
function obtenerObjetoLibro($idLibro) {
    // URL del endpoint para obtener el objeto libro por ID
    $url = "http://localhost:8080/BIBLIOTECA/libro/$idLibro";

    // Realizar la solicitud al endpoint
    $json = file_get_contents($url);

    // Decodificar la respuesta JSON
    $objetoLibro = json_decode($json);

    // Verificar si se encontró el libro
    if ($objetoLibro === null) {
        return null;
    }

    return $objetoLibro;
}

// Función para obtener el objeto usuario a partir del ID
function obtenerObjetoUsuario($idUsuario) {
    // URL del endpoint para obtener el objeto usuario por ID
    $url = "http://localhost:8080/BIBLIOTECA/usuario/$idUsuario";

    // Realizar la solicitud al endpoint
    $json = file_get_contents($url);

    // Decodificar la respuesta JSON
    $objetoUsuario = json_decode($json);

    // Verificar si se encontró el usuario
    if ($objetoUsuario === null) {
        return null;
    }

    return $objetoUsuario;
}
?>
