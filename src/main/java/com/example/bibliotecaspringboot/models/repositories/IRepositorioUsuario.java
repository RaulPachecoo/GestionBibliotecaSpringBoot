package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.EntidadUsuario;
import org.springframework.data.repository.CrudRepository;

public interface IRepositorioUsuario extends CrudRepository<EntidadUsuario,Integer> {

}
