package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.EntidadPrestamo;
import org.springframework.data.repository.CrudRepository;

public interface IRepositorioPrestamo extends CrudRepository<EntidadPrestamo, Integer> {

}
