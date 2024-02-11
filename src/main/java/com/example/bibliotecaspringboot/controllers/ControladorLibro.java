package com.example.bibliotecaspringboot.controllers;


import com.example.bibliotecaspringboot.models.entities.EntidadLibro;
import com.example.bibliotecaspringboot.models.entities.EntidadPrestamo;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/BIBLIOTECA/libro")
public class ControladorLibro {
    @Autowired
    private IRepositorioLibro libroRepositorio;

    //CRUD LIBROS


    @GetMapping
    public List<EntidadLibro> buscarLibros(){
        return (List<EntidadLibro>) libroRepositorio.findAll();
    }

    @GetMapping("/{id}") //búsqueda de libro por ID
    public ResponseEntity<EntidadLibro> buscarLibroPorId(@PathVariable(value = "id")int id){
        Optional<EntidadLibro> libro = libroRepositorio.findById(id);
        if (libro.isPresent())
            return ResponseEntity.ok().body(libro.get()); //200
        else return ResponseEntity.notFound().build(); //404
    }
    //Guardar libro nuevo
    @PostMapping
    public EntidadLibro guardarLibro(@Validated @RequestBody EntidadLibro libro){
        return libroRepositorio.save(libro);
    }

    //Borrar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarLibro(@PathVariable(value = "id")int id){
        if (libroRepositorio.existsById(id)){
            libroRepositorio.deleteById(id);
            return ResponseEntity.ok().body("LIBRO BORRADO");
        }else{
            return ResponseEntity.notFound().build();
        }
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
            libro.setPrestamosById(nuevoLibro.getPrestamosById());


            libroRepositorio.save(libro);
            return ResponseEntity.ok().body("Actualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
