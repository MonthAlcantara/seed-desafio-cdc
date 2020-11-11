package io.github.monthalcantara.apicasadocodigo.validator;

import io.github.monthalcantara.apicasadocodigo.model.Categoria;
import io.github.monthalcantara.apicasadocodigo.model.dto.request.CategoriaRequest;
import io.github.monthalcantara.apicasadocodigo.repository.CategoriaRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeNomeDuplicadoCategoriaValidator implements Validator {

    private CategoriaRepository categoriaRepository;

    public ProibeNomeDuplicadoCategoriaValidator(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoriaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }
        CategoriaRequest categoriaRequest = (CategoriaRequest) object;

        Optional<Categoria> possivelCategoria = categoriaRepository.findByNome(categoriaRequest.getNome());
        if (possivelCategoria.isPresent()) {
            errors.rejectValue("nome", null, "Ja existe uma outra categoria com o mesmo nome: " + categoriaRequest.getNome());
        }
    }
}
