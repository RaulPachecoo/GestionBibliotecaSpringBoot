package com.example.bibliotecaspringboot.controllers;


import com.example.bibliotecaspringboot.models.entities.EntidadCategoria;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/BIBLIOTECA/categoria")
public class ControladorCategoria {
    @Autowired
    private IRepositorioCategoria CategoriaRepositorio;

    //CRUD Categorias


    @GetMapping
    public List<EntidadCategoria> buscarCategorias(){
        return (List<EntidadCategoria>) CategoriaRepositorio.findAll();
    }

    @GetMapping("/{id}") //búsqueda de Categoria por ID
    public ResponseEntity<EntidadCategoria> buscarCategoriaPorId(@PathVariable(value = "id")int id){
        Optional<EntidadCategoria> Categoria = CategoriaRepositorio.findById(id);
        if (Categoria.isPresent())
            return ResponseEntity.ok().body(Categoria.get()); //200
        else return ResponseEntity.notFound().build(); //404
    }
    //Guardar Categoria nuevo
    @PostMapping
    public EntidadCategoria guardarCategoria(@Validated @RequestBody EntidadCategoria Categoria){
        return CategoriaRepositorio.save(Categoria);
    }

    //Borrar Categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarCategoria(@PathVariable(value = "id")int id){
        if (CategoriaRepositorio.existsById(id)){
            CategoriaRepositorio.deleteById(id);
            return ResponseEntity.ok().body("Categoria BORRADO");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //actualizar Categoria
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCategoria(@RequestBody EntidadCategoria nuevoCategoria, @PathVariable(value = "id") int idCategoria) {
        Optional<EntidadCategoria> CategoriaOpt = CategoriaRepositorio.findById(idCategoria);
        if (CategoriaOpt.isPresent()) {
            EntidadCategoria Categoria = CategoriaOpt.get();


            Categoria.setCategoria(nuevoCategoria.getCategoria());


            //Categoria.getListaPrestamos(nuevoCategoria.getListaPrestamos());


            CategoriaRepositorio.save(Categoria);
            return ResponseEntity.ok().body("Actualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}