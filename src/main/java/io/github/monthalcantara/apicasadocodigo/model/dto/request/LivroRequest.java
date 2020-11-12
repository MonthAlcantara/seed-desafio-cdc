package io.github.monthalcantara.apicasadocodigo.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.monthalcantara.apicasadocodigo.compartilhado.UniqueValue;
import io.github.monthalcantara.apicasadocodigo.model.Livro;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
//1
public class LivroRequest implements Serializable {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    private String sumario;

    @NotNull
    @Min(20)
    private Double preco;

    @NotNull
    @Min(100)
    private int numeroDePaginas;

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;

    @FutureOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataDePublicacao;

    @NotNull
    private Integer categoriaId;

    @NotNull
    private Integer autorId;

    @Deprecated
    public LivroRequest() {
    }

    public LivroRequest(@NotBlank String titulo,
                        @NotBlank @Size(max = 500) String resumo,
                        String sumario,
                        @NotNull @Min(20) Double preco,
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

    public Livro converteParaLivro(){
        return new Livro(this.titulo,
        this.resumo ,
        this.sumario,
        this.preco,
        this.numeroDePaginas,
        this.isbn,
        this.dataDePublicacao);
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
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

    public void setDataDePublicacao(LocalDate dataDePublicacao) {
        this.dataDePublicacao = dataDePublicacao;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getAutorId() {
        return autorId;
    }

    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }
}
