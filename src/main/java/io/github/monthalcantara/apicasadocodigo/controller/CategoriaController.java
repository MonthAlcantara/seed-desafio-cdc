package io.github.monthalcantara.apicasadocodigo.controller;

import io.github.monthalcantara.apicasadocodigo.model.Categoria;
import io.github.monthalcantara.apicasadocodigo.model.dto.request.CategoriaRequest;
import io.github.monthalcantara.apicasadocodigo.model.dto.response.CategoriaResponse;
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
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    private EntityManager entityManager;
//  private ProibeNomeDuplicadoCategoriaValidator proibeNomeDuplicadoCategoriaValidator;

    public CategoriaController(EntityManager em) {
//      this.proibeNomeDuplicadoCategoriaValidator = proibeNomeDuplicadoCategoriaValidator;
        this.entityManager = em;
    }

//    @InitBinder public void init(WebDataBinder binder) {binder.addValidators(proibeNomeDuplicadoCategoriaValidator);}


    @PostMapping
    @Transactional
    public ResponseEntity cadastraNovaCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest) {
        Categoria categoria = categoriaRequest.converteParaCategoria();
        entityManager.persist(categoria);
        return ResponseEntity.ok(new CategoriaResponse(categoria.getNome()));
    }

}
