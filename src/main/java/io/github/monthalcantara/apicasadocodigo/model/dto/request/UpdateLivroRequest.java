package io.github.monthalcantara.apicasadocodigo.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.monthalcantara.apicasadocodigo.compartilhado.ExistsId;
import io.github.monthalcantara.apicasadocodigo.model.Autor;
import io.github.monthalcantara.apicasadocodigo.model.Categoria;
import io.github.monthalcantara.apicasadocodigo.model.Livro;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdateLivroRequest implements Serializable {

    @NotBlank(message = "{livro.titulo.obrigatorio}")
    private String titulo;

    @NotBlank(message = "{livro.resumo.obrigatorio}")
    @Size(max = 500)
    private String resumo;

    private String sumario;

    @NotNull(message = "{livro.preco.obrigatorio}")
    @Min(value = 20, message = "{livro.preco.minimo}")
    private BigDecimal preco;

    @NotNull(message = "{livro.pagina.obrigatorio}")
    @Min(value = 100, message = "{livro.pagina.maximo}")
    private int numeroDePaginas;

    @NotBlank(message = "{livro.isbn.obrigatorio}")
    private String isbn;

    @FutureOrPresent(message = "{livro.data.invalido}")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataDePublicacao;

    @NotNull(message = "{livro.categoria.obrigatorio}")
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Integer categoriaId;

    @NotNull(message = "{livro.autor.obrigatorio}")
    @ExistsId(domainClass = Autor.class, fieldName = "id")
    private Integer autorId;

    @Deprecated
    public UpdateLivroRequest() {
    }

    public UpdateLivroRequest(@NotBlank String titulo,
                              @NotBlank @Size(max = 500) String resumo,
                              String sumario,
                              @NotNull @Min(20) BigDecimal preco,
                              @NotNull @Min(100) int numeroDePaginas,
                              @NotBlank String isbn,
                              @FutureOrPresent LocalDate dataDePublicacao) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataDePublicacao = dataDePublicacao;
    }

    public Livro converteParaLivro(EntityManager manager, Integer categoriaId, Integer autorId) {
        Categoria categoria = manager.find(Categoria.class, categoriaId);
        Assert.isTrue(categoria != null, "Não foi encontrada categoria com o id: " + categoriaId);

        Autor autor = manager.find(Autor.class, autorId);
        Assert.isTrue(autor != null, "Não foi encontrado(a) autor(a) com o id: " + autorId);

        return new Livro(this.titulo,
                this.resumo,
                this.sumario,
                this.preco,
                this.numeroDePaginas,
                this.isbn,
                this.dataDePublicacao,
                categoria,
                autor);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataDePublicacao() {
        return dataDePublicacao;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public Integer getAutorId() {
        return autorId;
    }

}
