package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.EntidadLibro;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRepositorioLibro extends CrudRepository<EntidadLibro, Integer> {
    @Override
    default <S extends EntidadLibro> S save(S entity) {
        return null;
    }

    @Override
    default <S extends EntidadLibro> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    default Optional<EntidadLibro> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    default boolean existsById(Integer integer) {
        return false;
    }

    @Override
    default Iterable<EntidadLibro> findAll() {
        return null;
    }

    @Override
    default Iterable<EntidadLibro> findAllById(Iterable<Integer> integers) {
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
    default void delete(EntidadLibro entity) {

    }

    @Override
    default void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    default void deleteAll(Iterable<? extends EntidadLibro> entities) {

    }

    @Override
    default void deleteAll() {

    }
}
