package io.github.monthalcantara.apicasadocodigo.compartilhado;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class}) // A classe que fará a implementação da validação (Padrão de nome é "NomeDaAnnotationValidator")
@Target({ElementType.FIELD}) // será utilizada em atributos
@Retention(RetentionPolicy.RUNTIME) // Sua atuação será em Runtime (Em tempo de execução)
public @interface UniqueValue {

    // Métodos obrigatórios de annotations

    String message() default "{io.github.monthalcantara.beanvalidation.uniquevalue}"; // Mensagem default que serpa aplicada quando a validação falhar

    Class<?>[] groups() default {}; // Se fosse escolher um grupo específico para aplicar a validação

    Class<? extends Payload>[] payload() default {}; // Para mandar alguma informação a mais para a validação

    /*
     * Informações específicas criadas para a validação. Ao anotar o atributo, a pessoa deverá informar os valores referentes a
     * esses dois campos. Diferente dos acima, eles não possuem um valor default. Precisam ser informados na hora
     */
    String fieldName();

    Class<?> domainClass();

}
