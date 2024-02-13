package com.example.bibliotecaspringboot.controllers;

import com.example.bibliotecaspringboot.models.entities.EntidadHistorico;
import com.example.bibliotecaspringboot.models.entities.EntidadPrestamo;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioHistorico;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioPrestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/BIBLIOTECA/historico")
public class ControladorHistorico {

    @Autowired
    private IRepositorioHistorico historicoRepositorio;

    // Obtener todos los préstamos
    @GetMapping //endpoint para buscar todos
    public ResponseEntity<?> buscarHistoricos() {
        List<EntidadHistorico> historicos = (List<EntidadHistorico>) historicoRepositorio.findAll();
        if (historicos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No hay préstamos disponibles");
        }
        return ResponseEntity.ok(historicos);
    }

    // Buscar un préstamo por su ID
    @GetMapping("/{idHistorico}")
    public ResponseEntity<EntidadHistorico> buscarHistoricoPorId(@PathVariable(value = "idHistorico") int idHistorico) {
        Optional<EntidadHistorico> historico = historicoRepositorio.findById(idHistorico);
        return historico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Guardar un nuevo préstamo
    @PostMapping
    public EntidadHistorico guardarPrestamo(@Validated @RequestBody EntidadHistorico historico) {
        return historicoRepositorio.save(historico);
    }

}
