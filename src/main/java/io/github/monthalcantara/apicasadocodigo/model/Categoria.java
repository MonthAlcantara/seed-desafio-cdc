package io.github.monthalcantara.apicasadocodigo.model;

import io.github.monthalcantara.apicasadocodigo.model.dto.response.CategoriaResponse;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

//2
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Livro> livros = new ArrayList<>();

    @Deprecated
    public Categoria(){}

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public CategoriaResponse converteParaResponse() {
       return new CategoriaResponse(this.nome);
    }
}
