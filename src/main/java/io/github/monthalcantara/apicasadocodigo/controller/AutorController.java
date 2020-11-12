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
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

//3
@RestController
@RequestMapping("/autores")
public class AutorController {

    @PersistenceContext
    private EntityManager entityManager;
//  private ProibeEmailDuplicadoAutorValidator proibeEmailDuplicadoAutorValidator;

    /*
     * Quando a requisição chega nesse controller, esse método é executado antes de chegar
     * ao endpoint de destino da requisição para executar as configurações necessarias para execução
     * desse request relativo a esse controller
     */
//    @InitBinder public void init(WebDataBinder binder) {binder.addValidators(proibeEmailDuplicadoAutorValidator); }

    public AutorController(EntityManager entityManager) {
        this.entityManager = entityManager;
//      this.proibeEmailDuplicadoAutorValidator = proibeEmailDuplicadoAutorValidator;
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastraNovoAutor(@RequestBody @Valid AutorRequest novoAutorRequest) {
        Autor novoAutor = novoAutorRequest.converteParaAutor();
        entityManager.persist(novoAutor);
        return new ResponseEntity(new AutorResponse(novoAutor), HttpStatus.OK);
    }
}
