package com.example.bibliotecaspringboot.models.repositories;

import com.example.bibliotecaspringboot.models.entities.EntidadPrestamo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IRepositorioPrestamo extends CrudRepository<EntidadPrestamo, Integer> {
    @Override
    default <S extends EntidadPrestamo> S save(S entity) {
        return null;
    }

    @Override
    default <S extends EntidadPrestamo> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    default Optional<EntidadPrestamo> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    default boolean existsById(Integer integer) {
        return false;
    }

    @Override
    default Iterable<EntidadPrestamo> findAll() {
        return null;
    }

    @Override
    default Iterable<EntidadPrestamo> findAllById(Iterable<Integer> integers) {
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
    default void delete(EntidadPrestamo entity) {

    }

    @Override
    default void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    default void deleteAll(Iterable<? extends EntidadPrestamo> entities) {

    }

    @Override
    default void deleteAll() {

    }
}
