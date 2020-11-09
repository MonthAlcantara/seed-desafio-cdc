package io.github.monthalcantara.apicasadocodigo.model;

import io.github.monthalcantara.apicasadocodigo.exception.RecursoNaoEncontradoException;
import io.github.monthalcantara.apicasadocodigo.model.dto.request.AutorRequest;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "{campo.nome.obrigatorio}")
    @Column(nullable = false)
    private String nome;

    @Email(message = "{campo.email.invalido}")
    @NotBlank(message = "{campo.email.obrigatorio}")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "{campo.descricao.obrigatorio}")
    @Size(max = 400)
    @Column(nullable = false)
    private String descricao;

    @PastOrPresent
    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    public Autor(@NotBlank String nome,
                 @Email @NotBlank String email,
                 @NotBlank
                 @Size(max = 400) String descricao,
                 @PastOrPresent LocalDateTime dataCriacao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
    }

    public Autor(AutorRequest autorDTO) {
        this.nome = autorDTO.getNome();
        this.email = autorDTO.getEmail();
        this.descricao = autorDTO.getDescricao();
        this.dataCriacao = autorDTO.getDataCriacao();
    }

    public Autor() {
    }

    public Integer getId() {
        if (id == null) {
            throw new RecursoNaoEncontradoException("O Autor não possui registro no banco de dados");
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(id, autor.id) &&
                Objects.equals(nome, autor.nome) &&
                Objects.equals(email, autor.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email);
    }
}
