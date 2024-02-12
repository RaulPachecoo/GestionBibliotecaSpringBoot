package com.example.bibliotecaspringboot.controllers;


import com.example.bibliotecaspringboot.models.entities.EntidadPrestamo;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioPrestamo;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<EntidadPrestamo> buscarPrestamos() {
        return (List<EntidadPrestamo>) prestamoRepositorio.findAll();
    }

    @GetMapping("/{id}") //endpoint para buscar un empleado por dni
    public ResponseEntity<EntidadPrestamo> buscarPrestamosPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadPrestamo> prestamo = prestamoRepositorio.findById(id);
        if (prestamo.isPresent())
            return ResponseEntity.ok().body(prestamo.get());// HTTP 200 OK
        else return ResponseEntity.notFound().build();      // HTTP 404
    }

    // Añadir un nuevo préstamo
    @PostMapping
    public EntidadPrestamo guardarPrestamo(@Validated @RequestBody EntidadPrestamo prestamo) {
        return prestamoRepositorio.save(prestamo);
    }

    // Borrar un préstamo por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPrestamo(@PathVariable(value = "id") int idPrestamo) {
        if (prestamoRepositorio.existsById(idPrestamo)) {
            prestamoRepositorio.deleteById(idPrestamo);
            return ResponseEntity.ok().body("Borrado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar un préstamo por su ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPrestamo(@RequestBody EntidadPrestamo nuevoPrestamo, @PathVariable(value = "id") int idPrestamo) {
        Optional<EntidadPrestamo> prestamoOpt = prestamoRepositorio.findById(idPrestamo);
        if (prestamoOpt.isPresent()) {
            EntidadPrestamo prestamo = prestamoOpt.get();
            prestamo.setFechaPrestamo(nuevoPrestamo.getFechaPrestamo());
            prestamo.setLibro(nuevoPrestamo.getLibro());
            prestamo.setUsuario(nuevoPrestamo.getUsuario());
            prestamoRepositorio.save(prestamo);
            return ResponseEntity.ok().body("Actualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
