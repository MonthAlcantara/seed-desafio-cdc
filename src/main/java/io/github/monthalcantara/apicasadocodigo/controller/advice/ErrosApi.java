package io.github.monthalcantara.apicasadocodigo.controller.advice;

import java.util.Arrays;
import java.util.List;

public class ErrosApi {

    List<String> erros;

    public ErrosApi(String e){
        this.erros = Arrays.asList(e);
    }

    public ErrosApi(List<String> listErrors) {
        this.erros = listErrors;
    }
}
