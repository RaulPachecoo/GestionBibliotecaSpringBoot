package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.EntidadCategoria;
import org.springframework.data.repository.CrudRepository;

public interface IRepositorioCategoria extends CrudRepository<EntidadCategoria, Integer> {

}
