package io.github.monthalcantara.apicasadocodigo.model.dto.request;

import io.github.monthalcantara.apicasadocodigo.compartilhado.UniqueValue;
import io.github.monthalcantara.apicasadocodigo.model.Autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class AutorRequest implements Serializable {

    @NotBlank(message = "{campo.nome.obrigatorio}")
    private String nome;

    @UniqueValue(fieldName = "email", domainClass = Autor.class)
    @NotBlank(message = "{campo.email.obrigatorio}")
    private String email;

    @NotBlank(message = "{campo.descricao.obrigatorio}")
    @Size(max = 400)
    private String descricao;

    public AutorRequest(@NotBlank String nome,
                        @Email @NotBlank String email,
                        @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor converteParaAutor() {
        return new Autor(this.nome, this.email, this.descricao);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutorRequest that = (AutorRequest) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
