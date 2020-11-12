package io.github.monthalcantara.apicasadocodigo.compartilhado;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
//0
/*
 * <Para qual annotation este validador trabalhará, O tipo de parametro esperado(Object, aceita todos os tipos)>
 */
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> klass;
    /*
     * Essa validação só vai funcionar no contexto do Spring. Por exemplo, ela não funcionaria na minha entity pois la o contexto é do hibernate
     * Funciona nas minhas classes de request pois elas estão no contexto do Spring
     */
    @PersistenceContext
    private EntityManager manager;

    public UniqueValueValidator(EntityManager manager) {
        this.manager = manager;
    }

    /*
     * Como o próprio nome sugere, será chamado ao instanciar a classe UniqueValueValidator
     *
     */
    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        //Capturando os atributos passados na anotação
        domainAttribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        /*
         *
         */
        Query query = manager.createQuery("Select 1 from " + klass.getName() + " where " +domainAttribute+ "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.isTrue(list.size() < 1,"Foi encontrado mais de um(a) " +klass.getSimpleName()+ " com o atributo '" +domainAttribute+ "' = " + value);
        return list.isEmpty();
    }
}
