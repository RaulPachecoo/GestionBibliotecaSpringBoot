package com.example.bibliotecaspringboot.controllers;

import com.example.bibliotecaspringboot.models.entities.EntidadPrestamo;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioPrestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/BIBLIOTECA/prestamos")
public class ControladorPrestamo {

    @Autowired
    private IRepositorioPrestamo prestamoRepositorio;

    // Obtener todos los préstamos
    @GetMapping //endpoint para buscar todos
    public ResponseEntity<?> buscarPrestamos() {
        List<EntidadPrestamo> prestamos = (List<EntidadPrestamo>) prestamoRepositorio.findAll();
        if (prestamos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay préstamos disponibles");
        }
        return ResponseEntity.ok(prestamos);
    }

    // Buscar un préstamo por su ID
    @GetMapping("/{id_prestamo}")
    public ResponseEntity<EntidadPrestamo> buscarPrestamoPorId(@PathVariable(value = "id_prestamo") int idPrestamo) {
        Optional<EntidadPrestamo> prestamo = prestamoRepositorio.findById(idPrestamo);
        return prestamo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Guardar un nuevo préstamo
    @PostMapping
    public EntidadPrestamo guardarPrestamo(@Validated @RequestBody EntidadPrestamo prestamo) {
        return prestamoRepositorio.save(prestamo);
    }

    // Borrar un préstamo por su ID
    @DeleteMapping("/{id_prestamo}")
    public ResponseEntity<?> borrarPrestamo(@PathVariable(value = "id_prestamo") int idPrestamo) {
        if (prestamoRepositorio.existsById(idPrestamo)) {
            prestamoRepositorio.deleteById(idPrestamo);
            return ResponseEntity.ok().body("Prestamo borrado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar un préstamo por su ID
    @PutMapping("/{id_prestamo}")
    public ResponseEntity<?> actualizarPrestamo(@RequestBody EntidadPrestamo nuevoPrestamo, @PathVariable(value = "id_prestamo") int idPrestamo) {
        Optional<EntidadPrestamo> prestamoOpt = prestamoRepositorio.findById(idPrestamo);
        if (prestamoOpt.isPresent()) {
            EntidadPrestamo prestamo = prestamoOpt.get();
            prestamo.setFechaPrestamo(nuevoPrestamo.getFechaPrestamo());
            prestamo.setLibro(nuevoPrestamo.getLibro());
            prestamo.setUsuario(nuevoPrestamo.getUsuario());
            prestamoRepositorio.save(prestamo);
            return ResponseEntity.ok().body("Prestamo actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
