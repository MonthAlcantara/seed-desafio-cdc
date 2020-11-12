package io.github.monthalcantara.apicasadocodigo.controller;

import io.github.monthalcantara.apicasadocodigo.model.Livro;
import io.github.monthalcantara.apicasadocodigo.model.dto.request.LivroRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

//1
@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager manager;

    public LivroController(EntityManager manager) {
        this.manager = this.manager;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastraNovoLivro(@RequestBody @Valid LivroRequest request) {
        Livro livro = request.converteParaLivro(manager, request.getCategoriaId(), request.getAutorId());
        manager.persist(livro);
        return ResponseEntity.ok(livro.converteParaResponse());
    }

}
