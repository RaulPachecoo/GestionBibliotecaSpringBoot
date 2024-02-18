package com.example.bibliotecaspringboot.controllers;


import com.example.bibliotecaspringboot.models.entities.EntidadHistorico;
import com.example.bibliotecaspringboot.models.entities.EntidadLibro;
import com.example.bibliotecaspringboot.models.helper.LogFile;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioHistorico;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/BIBLIOTECA/libro")
public class ControladorLibro {

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Autowired
    private IRepositorioLibro libroRepositorio;

    @Autowired
    private IRepositorioHistorico historicoRepositorio;

    //CRUD LIBROS

    @GetMapping
    public List<EntidadLibro> buscarLibros() {
        return (List<EntidadLibro>) libroRepositorio.findAll();
    }

    @GetMapping("/{id}") //búsqueda de libro por ID
    public ResponseEntity<EntidadLibro> buscarLibroPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadLibro> libro = libroRepositorio.findById(id);
        if (libro.isPresent())
            return ResponseEntity.ok().body(libro.get()); //200
        else return ResponseEntity.notFound().build(); //404
    }

    //Guardar libro nuevo
    @PostMapping
    public EntidadLibro guardarLibro(@Validated @RequestBody EntidadLibro libro) {
        EntidadLibro savedLibro = libroRepositorio.save(libro);
        try {
            grabaEnLogGuardar(libro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return savedLibro;
    }

    private void grabaEnLogGuardar(EntidadLibro libro) throws Exception {
        String info = "Save Libro(id, nombre, autor, editorial, categoria): (" + libro.getId() + ", " + libro.getNombre() + ", " + libro.getAutor() + ", " + libro.getEditorial() + ", " + libro.getCategoria().getId() + ")";
        LogFile.saveLOG(info);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        EntidadHistorico historico = new EntidadHistorico();
        historico.setUser(databaseUsername);
        historico.setFecha(now);
        historico.setInfo(info);
        historicoRepositorio.save(historico);
    }

    //Borrar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarLibro(@PathVariable(value = "id") int id) {
        try {
            grabaEnLogBorrar(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (libroRepositorio.existsById(id)) {
            libroRepositorio.deleteById(id);
            return ResponseEntity.ok().body("LIBRO BORRADO");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void grabaEnLogBorrar(int idLibro) throws Exception {
        String info = "Delete Libro: (id: " + idLibro + ")";
        LogFile.saveLOG(info);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        EntidadHistorico historico = new EntidadHistorico();
        historico.setUser(databaseUsername);
        historico.setFecha(now);
        historico.setInfo(info);
        historicoRepositorio.save(historico);
    }

    //actualizar libro
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarLibro(@RequestBody EntidadLibro nuevoLibro, @PathVariable(value = "id") int idLibro) {
        Optional<EntidadLibro> libroOpt = libroRepositorio.findById(idLibro);
        if (libroOpt.isPresent()) {
            EntidadLibro libro = libroOpt.get();
            libro.setAutor(nuevoLibro.getAutor());
            libro.setNombre(nuevoLibro.getNombre());
            libro.setEditorial(nuevoLibro.getEditorial());
            libro.setCategoria(nuevoLibro.getCategoria());

            libroRepositorio.save(libro);
            try {
                grabaEnLogActualizar(nuevoLibro, idLibro);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok().body("Actualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void grabaEnLogActualizar(EntidadLibro nuevoLibro, int idLibro) throws Exception {
        String info = "Update Libro(id, nombre, autor, editorial, categoria): (id: " + idLibro + ") -> (" + nuevoLibro.getId() + ", " + nuevoLibro.getNombre() + ", " + nuevoLibro.getAutor() + ", " + nuevoLibro.getEditorial() + ", " + nuevoLibro.getCategoria().getId() + ")";
        LogFile.saveLOG(info);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        EntidadHistorico historico = new EntidadHistorico();
        historico.setUser(databaseUsername);
        historico.setFecha(now);
        historico.setInfo(info);
        historicoRepositorio.save(historico);
    }
}
