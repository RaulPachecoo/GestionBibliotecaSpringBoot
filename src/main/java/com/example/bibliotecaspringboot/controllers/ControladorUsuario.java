package com.example.bibliotecaspringboot.controllers;

import com.example.bibliotecaspringboot.models.entities.EntidadHistorico;
import com.example.bibliotecaspringboot.models.entities.EntidadUsuario;
import com.example.bibliotecaspringboot.models.helper.LogFile;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioHistorico;
import com.example.bibliotecaspringboot.models.repositories.IRepositorioUsuario;
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
@RequestMapping("/BIBLIOTECA/usuario")
public class ControladorUsuario {

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Autowired
    private IRepositorioUsuario usuarioRepositorio;

    @Autowired
    private IRepositorioHistorico historicoRepositorio;

    //CRUD USUARIO

    @GetMapping
    public List<EntidadUsuario> buscarUsuarios() {
        return (List<EntidadUsuario>) usuarioRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadUsuario> buscarUsuarioPorId(@PathVariable(value = "id") int idUsuario) {
        Optional<EntidadUsuario> usuario = usuarioRepositorio.findById(idUsuario);
        if (usuario.isPresent())
            return ResponseEntity.ok().body(usuario.get());
        else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public EntidadUsuario guardarUsuario(@Validated @RequestBody EntidadUsuario usuario) {

        EntidadUsuario savedUsuario = usuarioRepositorio.save(usuario);
        try {
            grabaEnLogGuardar(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return savedUsuario;
    }

    private void grabaEnLogGuardar(EntidadUsuario usuario) throws Exception {
        String info = "Save Usuario(id, nombre, apellidos): (" + usuario.getId() + ", " + usuario.getNombre() + ", " + usuario.getApellidos() + ")";
        LogFile.saveLOG(info);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        EntidadHistorico historico = new EntidadHistorico();
        historico.setUser(databaseUsername);
        historico.setFecha(now);
        historico.setInfo(info);
        historicoRepositorio.save(historico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable(value = "id") int id) {
        try {
            grabaEnLogBorrar(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (usuarioRepositorio.existsById(id)) {
            usuarioRepositorio.deleteById(id);
            return ResponseEntity.ok().body("USUARIO BORRADO");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void grabaEnLogBorrar(int idUsuario) throws Exception {
        String info = "Delete Usuario: (id: " + idUsuario + ")";
        LogFile.saveLOG(info);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        EntidadHistorico historico = new EntidadHistorico();
        historico.setUser(databaseUsername);
        historico.setFecha(now);
        historico.setInfo(info);
        historicoRepositorio.save(historico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarUsuario(@RequestBody EntidadUsuario nuevoUsuario, @PathVariable(value = "id") int idUsuario) {
        Optional<EntidadUsuario> usuarioOptional = usuarioRepositorio.findById(idUsuario);
        if (usuarioOptional.isPresent()) {
            EntidadUsuario usuario = usuarioOptional.get();

            usuario.setNombre(nuevoUsuario.getNombre());
            usuario.setApellidos(nuevoUsuario.getApellidos());
            usuario.setListaPrestamos(nuevoUsuario.getListaPrestamos());

            usuarioRepositorio.save(usuario);
            try {
                grabaEnLogActualizar(nuevoUsuario, idUsuario);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return ResponseEntity.ok().body("ACTUALIZADO");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void grabaEnLogActualizar(EntidadUsuario nuevoUsuario, int idUsuario) throws Exception {
        String info = "Update Usuario(id, nombre, apellidos): (id: " + idUsuario + ") -> (" + nuevoUsuario.getId() + ", " + nuevoUsuario.getNombre() + ", " + nuevoUsuario.getApellidos() + ")";
        LogFile.saveLOG(info);
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        EntidadHistorico historico = new EntidadHistorico();
        historico.setUser(databaseUsername);
        historico.setFecha(now);
        historico.setInfo(info);
        historicoRepositorio.save(historico);
    }
}
