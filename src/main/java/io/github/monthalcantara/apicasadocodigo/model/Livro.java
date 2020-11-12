package io.github.monthalcantara.apicasadocodigo.model;

import io.github.monthalcantara.apicasadocodigo.model.dto.response.CategoriaResponse;
import io.github.monthalcantara.apicasadocodigo.model.dto.response.LivroResponse;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false)
    @Size(max = 500)
    private String resumo;

    private String sumario;

    @Column(nullable = false)
    @Min(20)
    private Double preco;

    @Column(nullable = false)
    @Min(100)
    private int numeroDePaginas;

    @Column(nullable = false, unique = true)
    private String isbn;

    @FutureOrPresent
    private LocalDate dataDePublicacao;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Deprecated
    public Livro() {
    }

    public Livro(@NotBlank String titulo,
                 @Size(max = 500) String resumo,
                 String sumario,
                 @Min(20) Double preco,
                 @Min(100) int numeroDePaginas,
                 String isbn,
                 @FutureOrPresent LocalDate dataDePublicacao) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataDePublicacao = dataDePublicacao;
    }

    public LivroResponse converteParaResponse(){
        return new LivroResponse(this.titulo,
        this.resumo,
        this.sumario,
        this.preco,
        this.numeroDePaginas,
        this.isbn,
        this.dataDePublicacao,
        this.categoria.converteParaResponse(),
        this.autor.converteParaResponse());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
