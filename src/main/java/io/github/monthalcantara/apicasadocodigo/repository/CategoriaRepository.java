package io.github.monthalcantara.apicasadocodigo.repository;

import io.github.monthalcantara.apicasadocodigo.model.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {

    Optional<Categoria> findByNome(String nome);
}
