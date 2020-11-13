package io.github.monthalcantara.apicasadocodigo.compartilhado;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

//0
public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    private String fieldName;
    private Class<?> domainClass;
    @PersistenceContext
    private EntityManager em;

    public ExistsIdValidator(EntityManager em) {
        this.em = em;
    }

    @Override
    public void initialize(ExistsId constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
        domainClass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = em.createQuery("Select 1 from " + domainClass.getSimpleName() + " where " + fieldName + " =:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();

        Assert.isTrue(!list.isEmpty(), "Não existe um " + domainClass.getSimpleName() + " com o atributo " + fieldName + " igual a " + value);
        return !list.isEmpty();
    }
}
