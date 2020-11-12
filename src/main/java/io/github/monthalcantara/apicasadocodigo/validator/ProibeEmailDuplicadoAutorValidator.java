package io.github.monthalcantara.apicasadocodigo.validator;


import io.github.monthalcantara.apicasadocodigo.model.Autor;
import io.github.monthalcantara.apicasadocodigo.model.dto.request.AutorRequest;
import io.github.monthalcantara.apicasadocodigo.repository.AutorRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;
//4
@Component
public class ProibeEmailDuplicadoAutorValidator implements Validator {

    private AutorRepository autorRepository;

    public ProibeEmailDuplicadoAutorValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    /*
     * Qual o tipo de parametro que eu devo aplicar essa validação?
     * Qual classe será suportada por essa validação? ( Nesse caso, AutorRequest)
     */
    @Override
    public boolean supports(Class<?> aClass) {
        /*
         * Verificando se a classe que está chegando como argumento (aClass) é a mesma, ou a filha de AutorRequest
         */
        return AutorRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        /*
         * Verificando se já existe algum outro erro na execução. Se existir, nem completa a validação.
         */
        if (errors.hasErrors()) {
            return;
        }
        /*
         * Se a execução chegou até aqui é por que a classe que chega como argumento é a mesma, ou a filha de AutorRequest
         * Nesse caso posso fazer um cast de forma segura
         */
        AutorRequest request = (AutorRequest) target;

        Optional<Autor> possivelAutor = autorRepository.findByEmail(request.getEmail());
        /*
        * A ideia é nunca alterar um objeto que não fui eu quem criou, diferente do que
        * estou fazendo com o Errors, por que isso impede oo acompanhamento do estado do objeto num determinado fluxo.
        * Excessão a regra é que como o Errors foi criado na borda externa, o Spring ja sabe que pode ser alterado
         */
        if(possivelAutor.isPresent()){
            errors.rejectValue("email", null,
                    "Ja existe um(a) outro(a) autor(a) com o mesmo email: "
                            + request.getEmail());
        }
    }
}
