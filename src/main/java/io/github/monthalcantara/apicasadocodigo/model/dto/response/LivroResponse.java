package io.github.monthalcantara.apicasadocodigo.model.dto.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class LivroResponse implements Serializable {

    private String titulo;

    private String resumo;

    private String sumario;

    private Double preco;

    private int numeroDePaginas;

    private String isbn;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDePublicacao;

    private CategoriaResponse categoria;

    private AutorResponse autor;

    @Deprecated
    public LivroResponse() {
    }

    public LivroResponse(String titulo,
                         String resumo,
                         String sumario,
                         Double preco,
                         int numeroDePaginas,
                         String isbn,
                         LocalDate dataDePublicacao,
                         CategoriaResponse categoria,
                         AutorResponse autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataDePublicacao = dataDePublicacao;
        this.categoria = categoria;
        this.autor = autor;
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

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaResponse categoria) {
        this.categoria = categoria;
    }

    public AutorResponse getAutor() {
        return autor;
    }

    public void setAutor(AutorResponse autor) {
        this.autor = autor;
    }
}
