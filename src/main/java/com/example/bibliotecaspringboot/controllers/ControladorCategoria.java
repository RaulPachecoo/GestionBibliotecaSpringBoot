package com.example.bibliotecaspringboot.controllers;


import com.example.bibliotecaspringboot.models.entities.EntidadCategoria;
import com.example.bibliotecaspringboot.models.entities.EntidadHistorico;
import com.example.bibliotecaspringboot.models.helper.LogFile;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioCategoria;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioHistorico;
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
@RequestMapping("/BIBLIOTECA/categoria")
public class ControladorCategoria {

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Autowired
    private IRepositorioCategoria CategoriaRepositorio;

    @Autowired
    private IRepositorioHistorico historicoRepositorio;

    //CRUD Categorias

    @GetMapping
    public List<EntidadCategoria> buscarCategorias() {
        return (List<EntidadCategoria>) CategoriaRepositorio.findAll();
    }

    @GetMapping("/{id}") //búsqueda de Categoria por ID
    public ResponseEntity<EntidadCategoria> buscarCategoriaPorId(@PathVariable(value = "id") int id) {
        Optional<EntidadCategoria> Categoria = CategoriaRepositorio.findById(id);
        if (Categoria.isPresent())
            return ResponseEntity.ok().body(Categoria.get()); //200
        else return ResponseEntity.notFound().build(); //404
    }

    //Guardar Categoria nuevo
    @PostMapping
    public EntidadCategoria guardarCategoria(@Validated @RequestBody EntidadCategoria Categoria) {
        EntidadCategoria savedCategoria = CategoriaRepositorio.save(Categoria);
        try {
            grabaEnLogGuardar(Categoria);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return savedCategoria;
    }

    private void grabaEnLogGuardar(EntidadCategoria categoria) throws Exception {
        String info = "Save Categoria(id, categoria): (" + categoria.getId() + ", " + categoria.getCategoria() + ")";
        LogFile.saveLOG(info);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        EntidadHistorico historico = new EntidadHistorico();
        historico.setUser(databaseUsername);
        historico.setFecha(now);
        historico.setInfo(info);
        historicoRepositorio.save(historico);
    }

    //Borrar Categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarCategoria(@PathVariable(value = "id") int id) {
        try {
            grabaEnLogBorrar(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (CategoriaRepositorio.existsById(id)) {
            CategoriaRepositorio.deleteById(id);
            return ResponseEntity.ok().body("Categoria BORRADO");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void grabaEnLogBorrar(int idCategoria) throws Exception {
        String info = "Delete Categoria: (id: " + idCategoria + ")";
        LogFile.saveLOG(info);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        EntidadHistorico historico = new EntidadHistorico();
        historico.setUser(databaseUsername);
        historico.setFecha(now);
        historico.setInfo(info);
        historicoRepositorio.save(historico);
    }

    //actualizar Categoria
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCategoria(@RequestBody EntidadCategoria nuevoCategoria, @PathVariable(value = "id") int idCategoria) {
        Optional<EntidadCategoria> CategoriaOpt = CategoriaRepositorio.findById(idCategoria);
        if (CategoriaOpt.isPresent()) {
            EntidadCategoria Categoria = CategoriaOpt.get();
            Categoria.setCategoria(nuevoCategoria.getCategoria());


            CategoriaRepositorio.save(Categoria);
            try {
                grabaEnLogActualizar(nuevoCategoria, idCategoria);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok().body("Actualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void grabaEnLogActualizar(EntidadCategoria nuevaCategoria, int idCategoria) throws Exception {
        String info = "Update Categoria(id, categoria): (id: " + idCategoria + ") -> (" + nuevaCategoria.getId() + ", " + nuevaCategoria.getCategoria() + ")";
        LogFile.saveLOG(info);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        EntidadHistorico historico = new EntidadHistorico();
        historico.setUser(databaseUsername);
        historico.setFecha(now);
        historico.setInfo(info);
        historicoRepositorio.save(historico);
    }
}
