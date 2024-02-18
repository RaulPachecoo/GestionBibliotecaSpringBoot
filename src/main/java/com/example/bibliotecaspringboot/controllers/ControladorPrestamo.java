package com.example.bibliotecaspringboot.controllers;


import com.example.bibliotecaspringboot.models.entities.EntidadHistorico;
import com.example.bibliotecaspringboot.models.entities.EntidadPrestamo;
import com.example.bibliotecaspringboot.models.helper.LogFile;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioHistorico;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioPrestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/BIBLIOTECA/prestamos")
public class ControladorPrestamo {

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Autowired
    private IRepositorioPrestamo prestamoRepositorio;

    @Autowired
    private IRepositorioHistorico repositorioHistorico;

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
        try {
            grabaEnLogIns(prestamo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return prestamoRepositorio.save(prestamo);
    }

    private void grabaEnLogIns(EntidadPrestamo prestamo) throws Exception {

        String info = "Save Prestamo(idPrestamo, fechaPrestamo, idLibro, idUsuario): (" + prestamo.getIdPrestamo() + ", " + prestamo.getFechaPrestamo() + ", " + prestamo.getLibro().getId() + ", " + prestamo.getUsuario().getId() + ")";
        LogFile.saveLOG(info);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        EntidadHistorico historico = new EntidadHistorico();
        historico.setUser(databaseUsername);
        historico.setFecha(now);
        historico.setInfo(info);
        repositorioHistorico.save(historico);
    }

    // Borrar un préstamo por su ID
    @DeleteMapping("/{id_prestamo}")
    public ResponseEntity<?> borrarPrestamo(@PathVariable(value = "id_prestamo") int idPrestamo) {

        try {
            grabaEnLogBorrar(idPrestamo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (prestamoRepositorio.existsById(idPrestamo)) {
            prestamoRepositorio.deleteById(idPrestamo);
            return ResponseEntity.ok().body("Prestamo borrado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    private void grabaEnLogBorrar(int idPrestamo) throws Exception {
        String info = "Delete Prestamo: (id: " + idPrestamo +")";
        LogFile.saveLOG(info);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        EntidadHistorico historico = new EntidadHistorico();
        historico.setUser(databaseUsername);
        historico.setFecha(now);
        historico.setInfo(info);
        repositorioHistorico.save(historico);
    }

    // Actualizar un préstamo por su ID
    @PutMapping("/{id_prestamo}")
    public ResponseEntity<?> actualizarPrestamo(@RequestBody EntidadPrestamo nuevoPrestamo, @PathVariable(value = "id_prestamo") int idPrestamo) {
        try {
            grabaEnLogActualizar(nuevoPrestamo, idPrestamo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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

    private void grabaEnLogActualizar(EntidadPrestamo nuevoPrestamo, int idPrestamo) throws Exception {
        String info = "Update Prestamo(idPrestamo, fechaPrestamo, idLibro, idUsuario): (id: " + idPrestamo + ") -> (" +  nuevoPrestamo.getIdPrestamo() + ", " + nuevoPrestamo.getFechaPrestamo() + ", " + nuevoPrestamo.getLibro().getId() + ", " + nuevoPrestamo.getUsuario().getId() + ")";
        LogFile.saveLOG(info);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        EntidadHistorico historico = new EntidadHistorico();
        historico.setUser(databaseUsername);
        historico.setFecha(now);
        historico.setInfo(info);
        repositorioHistorico.save(historico);
    }
}
