package com.bao.crm.validation.Impl;

import com.bao.crm.validation.FieldMatch;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchImpl implements ConstraintValidator<FieldMatch, Object> {


    private String firstFieldName;
    private String secondFieldName;
    private String messege;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
        messege = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object password, ConstraintValidatorContext context) {
        boolean result = true;
        try{
            Object firstObj = new BeanWrapperImpl(password).getPropertyValue(firstFieldName);
            Object secondObj = new BeanWrapperImpl(password).getPropertyValue(secondFieldName);

            result = firstObj == null && secondObj == null
                    || firstObj != null && firstObj.equals(secondObj);


        }catch (Exception  e){
                //
        }

        if (!result){
            context.buildConstraintViolationWithTemplate(messege)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return result;
    }
}
