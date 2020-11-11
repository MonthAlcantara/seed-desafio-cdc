package io.github.monthalcantara.apicasadocodigo.model.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.monthalcantara.apicasadocodigo.compartilhado.UniqueValue;
import io.github.monthalcantara.apicasadocodigo.model.Categoria;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class CategoriaRequest implements Serializable {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class)
    private String nome;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) // Evita bug do jackson com construtores com apenas um parametro
    public CategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    @Deprecated
    public CategoriaRequest() {
    }

    public Categoria converteParaCategoria() {
        return new Categoria(this.nome);
    }

    public String getNome() {
        return nome;
    }
}
