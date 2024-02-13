package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.EntidadHistorico;
import com.example.bibliotecaspringboot.models.entities.EntidadPrestamo;
import org.springframework.data.repository.CrudRepository;

public interface IRepositorioHistorico extends CrudRepository<EntidadHistorico, Integer> {

}
