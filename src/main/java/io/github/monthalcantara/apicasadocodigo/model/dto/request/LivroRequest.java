package io.github.monthalcantara.apicasadocodigo.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.monthalcantara.apicasadocodigo.compartilhado.ExistsId;
import io.github.monthalcantara.apicasadocodigo.compartilhado.UniqueValue;
import io.github.monthalcantara.apicasadocodigo.model.Autor;
import io.github.monthalcantara.apicasadocodigo.model.Categoria;
import io.github.monthalcantara.apicasadocodigo.model.Livro;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

//4
public class LivroRequest implements Serializable {

    @NotBlank(message = "{livro.titulo.obrigatorio}")
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
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
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;

    /*
     * Shape = Tipo de dado que será recebido
     * Caso eu fosse receber a data por um form padrão a annotation a ser utilizada é @DateTimeFormat(pattern = "dd/MM/yyyy")
     */
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
    public LivroRequest() {
    }

    public LivroRequest(@NotBlank String titulo,
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

    /*
     * O Jackson está apresentando problema para desserializar o Json com a data como parametro no construtor.
     * Por isso foi criado este set.
     */
    public void setDataDePublicacao(LocalDate dataDePublicacao) {
        this.dataDePublicacao = dataDePublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDataDePublicacao() {
        return dataDePublicacao;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public Integer getAutorId() {
        return autorId;
    }

}
