package io.learning;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;


/**
 * All these tests are normally done in unit test. Here its just for the sake of triggering failsafe plugin
 */

public class AppIT {

    private Person person;
    private final static int LENGTH = 3;

    private Validator validator;


    @Before
    public void init(){
        person = new Person("you", "you", "yougmail.com");
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Test
    public void usernameShouldHaveMinLenght(){
        Assert.assertEquals(LENGTH, person.getUsername().length());
    }

    @Test
    public void emailShouldBeInvalid(){
        List<ConstraintViolation<Person>> constraintViolations = new ArrayList<>(validator.validateValue(Person.class, "email", person.getEmail()));
        Assert.assertFalse(constraintViolations.get(0).getMessage(), constraintViolations.isEmpty());
    }

    @Test
    public void usernameShouldBeValid(){
        List<ConstraintViolation<Person>> constraintViolations = new ArrayList<>(validator.validateProperty(person, "username"));
        Assert.assertTrue(constraintViolations.isEmpty());
    }

    @Test
    public void personShouldBeInvalid(){
        List<ConstraintViolation<Person>> constraintViolations = new ArrayList<>(validator.validate(person));
        StringBuilder stringBuilder = new StringBuilder();
        constraintViolations.stream().forEach(constraint -> stringBuilder.append(constraint.getMessage()).append(" "));
        Assert.assertFalse(stringBuilder.toString(), constraintViolations.isEmpty());
    }




}
