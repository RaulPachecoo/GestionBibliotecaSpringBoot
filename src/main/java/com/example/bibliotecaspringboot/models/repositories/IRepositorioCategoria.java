package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.EntidadCategoria;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRepositorioCategoria extends CrudRepository<EntidadCategoria, Integer> {
    @Override
    default <S extends EntidadCategoria> S save(S entity) {
        return null;
    }

    @Override
    default <S extends EntidadCategoria> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    default Optional<EntidadCategoria> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    default boolean existsById(Integer integer) {
        return false;
    }

    @Override
    default Iterable<EntidadCategoria> findAll() {
        return null;
    }

    @Override
    default Iterable<EntidadCategoria> findAllById(Iterable<Integer> integers) {
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
    default void delete(EntidadCategoria entity) {

    }

    @Override
    default void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    default void deleteAll(Iterable<? extends EntidadCategoria> entities) {

    }

    @Override
    default void deleteAll() {

    }
}
