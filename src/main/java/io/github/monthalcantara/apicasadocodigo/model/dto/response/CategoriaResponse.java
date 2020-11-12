package io.github.monthalcantara.apicasadocodigo.model.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.github.monthalcantara.apicasadocodigo.model.Categoria;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

//0
public class CategoriaResponse implements Serializable {

    @NotBlank
    private String nome;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) // Evita bug do jackson com construtores com apenas um parametro
    public CategoriaResponse(@NotBlank String nome) {
        this.nome = nome;
    }

    @Deprecated
    public CategoriaResponse() {
    }

    public CategoriaResponse converteParaCategoriaResponse(Categoria categoria){
        return new CategoriaResponse(categoria.getNome());
    }

    public String getNome() {
        return nome;
    }
}
