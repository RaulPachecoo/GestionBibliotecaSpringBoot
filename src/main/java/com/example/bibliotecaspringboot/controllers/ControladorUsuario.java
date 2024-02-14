package com.example.bibliotecaspringboot.controllers;


import com.example.bibliotecaspringboot.models.entities.EntidadUsuario;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioLibro;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/BIBLIOTECA/usuario")
public class ControladorUsuario {
    @Autowired
    private IRepositorioUsuario usuarioRepositorio;

    //CRUD USUARIO

    @GetMapping
    public List<EntidadUsuario> buscarUsuarios(){
        return (List<EntidadUsuario>) usuarioRepositorio.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<EntidadUsuario> buscarUsuarioPorId(@PathVariable(value = "id")int idUsuario){
        Optional<EntidadUsuario> usuario = usuarioRepositorio.findById(idUsuario);
        if (usuario.isPresent())
            return ResponseEntity.ok().body(usuario.get());
        else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public EntidadUsuario guardarUsuario(@Validated @RequestBody EntidadUsuario usuario){
        return usuarioRepositorio.save(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable(value = "id")int id){
        if (usuarioRepositorio.existsById(id)){
            usuarioRepositorio.deleteById(id);
            return ResponseEntity.ok().body("USUARIO BORRADO");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@RequestBody EntidadUsuario nuevoUsuario,@PathVariable(value = "id")int idUsuario){
        Optional<EntidadUsuario> usuarioOptional = usuarioRepositorio.findById(idUsuario);
        if (usuarioOptional.isPresent()){
            EntidadUsuario usuario = usuarioOptional.get();

            usuario.setNombre(nuevoUsuario.getNombre());
            usuario.setApellidos(nuevoUsuario.getApellidos());
            usuario.setListaPrestamos(nuevoUsuario.getListaPrestamos());

            usuarioRepositorio.save(usuario);
            return ResponseEntity.ok().body("ACTUALIZADO");
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
