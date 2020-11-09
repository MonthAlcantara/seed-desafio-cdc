package io.github.monthalcantara.apicasadocodigo.controller;

import io.github.monthalcantara.apicasadocodigo.model.Autor;
import io.github.monthalcantara.apicasadocodigo.model.dto.request.AutorRequest;
import io.github.monthalcantara.apicasadocodigo.model.dto.response.AutorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private EntityManager entityManager;

    public AutorController(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastraNovoAutor(@RequestBody @Valid AutorRequest novoAutorRequest){
        Autor novoAutor = new Autor(novoAutorRequest);
        entityManager.persist(novoAutor);
        return new ResponseEntity(new AutorResponse(novoAutor), HttpStatus.OK);
    }
}
