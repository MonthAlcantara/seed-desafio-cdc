package io.github.monthalcantara.apicasadocodigo.controller;

import io.github.monthalcantara.apicasadocodigo.exception.RecursoNaoEncontradoException;
import io.github.monthalcantara.apicasadocodigo.model.Livro;
import io.github.monthalcantara.apicasadocodigo.model.dto.request.LivroRequest;
import io.github.monthalcantara.apicasadocodigo.model.dto.request.UpdateLivroRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

//5
@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager manager;

    public LivroController(EntityManager manager) {
        this.manager = this.manager;
    }

    //1
    @Transactional
    @PostMapping
    public ResponseEntity cadastraNovoLivro(@RequestBody @Valid LivroRequest request) {
        //1
        Livro livro = request.converteParaLivro(manager, request.getCategoriaId(), request.getAutorId());
        manager.persist(livro);
        return ResponseEntity.ok(livro.converteParaResponse());
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity buscaLivroPeloId(@PathVariable("id") Integer id) {
        Livro livro = buscalivroPeloId(id);
        return ResponseEntity.ok(livro.converteParaResponse());
    }

    //1
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity atualizaLivro(@RequestBody @Valid UpdateLivroRequest request, @PathVariable("id") Integer id) {
        buscalivroPeloId(id);
        Livro novoLivro = request.converteParaLivro(manager, request.getCategoriaId(), request.getAutorId());
        novoLivro.setId(id);
        manager.merge(novoLivro);
        return ResponseEntity.ok(novoLivro.converteParaResponse());
    }

    private Livro buscalivroPeloId(Integer id) {
        Livro livro = manager.find(Livro.class, id);
        //1

        if (livro == null) {
            //1
            throw new RecursoNaoEncontradoException("Não existe livro com esse id");
        }
        return livro;
    }

}
