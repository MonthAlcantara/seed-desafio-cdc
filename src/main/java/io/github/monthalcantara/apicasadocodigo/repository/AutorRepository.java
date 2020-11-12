package io.github.monthalcantara.apicasadocodigo.repository;

import io.github.monthalcantara.apicasadocodigo.model.Autor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//1
public interface AutorRepository extends CrudRepository<Autor, Integer> {

    Optional<Autor> findByEmail(String email);
}
