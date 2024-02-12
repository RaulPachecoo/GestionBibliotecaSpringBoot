package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.EntidadLibro;
import org.springframework.data.repository.CrudRepository;

public interface IRepositorioLibro extends CrudRepository<EntidadLibro, Integer> {

}
