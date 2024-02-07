package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.EntidadCategoria;
import com.example.bibliotecaspringboot.models.entities.EntidadUsuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRepositorioUsuario extends CrudRepository<EntidadUsuario,Integer> {
    @Override
    default <S extends EntidadUsuario> S save(S entity) {
        return null;
    }

    @Override
    default <S extends EntidadUsuario> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    default Optional<EntidadUsuario> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    default boolean existsById(Integer integer) {
        return false;
    }

    @Override
    default Iterable<EntidadUsuario> findAll() {
        return null;
    }

    @Override
    default Iterable<EntidadUsuario> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    default long count() {
        return 0;
    }

    @Override
    default void deleteById(Integer integer) {

    }

    @Override
    default void delete(EntidadUsuario entity) {

    }

    @Override
    default void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    default void deleteAll(Iterable<? extends EntidadUsuario> entities) {

    }

    @Override
    default void deleteAll() {

    }
}
