package io.github.monthalcantara.apicasadocodigo.controller;

import io.github.monthalcantara.apicasadocodigo.model.Livro;
import io.github.monthalcantara.apicasadocodigo.model.dto.request.LivroRequest;
import io.github.monthalcantara.apicasadocodigo.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity cadastraNovoLivro(@RequestBody @Valid LivroRequest request){
        Livro livro = livroService.cadastraNovoLivro(request.converteParaLivro(), request.getCategoriaId(), request.getAutorId());
        return ResponseEntity.ok(livro.converteParaResponse());
    }

}
