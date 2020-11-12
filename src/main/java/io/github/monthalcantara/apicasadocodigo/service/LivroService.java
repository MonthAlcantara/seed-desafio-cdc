package io.github.monthalcantara.apicasadocodigo.service;

import io.github.monthalcantara.apicasadocodigo.exception.RecursoNaoEncontradoException;
import io.github.monthalcantara.apicasadocodigo.model.Autor;
import io.github.monthalcantara.apicasadocodigo.model.Categoria;
import io.github.monthalcantara.apicasadocodigo.model.Livro;
import io.github.monthalcantara.apicasadocodigo.repository.AutorRepository;
import io.github.monthalcantara.apicasadocodigo.repository.CategoriaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;
//4
@Component
public class LivroService {

    private AutorRepository autorRepository;

    private CategoriaRepository categoriaRepository;

    @PersistenceContext
    private EntityManager manager;

    public LivroService(AutorRepository autorRepository,
                        CategoriaRepository categoriaRepository,
                        EntityManager manager) {
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
        this.manager = manager;
    }

    @Transactional
    public Livro cadastraNovoLivro(Livro livro, Integer categoriaId, Integer autorId) {
        Optional<Autor> possivelAutor = autorRepository.findById(autorId);
        Optional<Categoria> possivelCategoria = categoriaRepository.findById(categoriaId);

        Autor autor = possivelAutor.orElseThrow(() -> new RecursoNaoEncontradoException("Não foi encontrado autor com o id: " + autorId));
        Categoria categoria = possivelCategoria.orElseThrow(() -> new RecursoNaoEncontradoException("Não foi encontrado categoria com o id: " + categoriaId));
        livro.setCategoria(categoria);
        livro.setAutor(autor);
        manager.persist(livro);
        return livro;
    }
}
