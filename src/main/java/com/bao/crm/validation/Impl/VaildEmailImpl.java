package com.bao.crm.validation.Impl;

import com.bao.crm.validation.VaildEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VaildEmailImpl implements ConstraintValidator<VaildEmail, String> {
    @Override
    public void initialize(VaildEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        if(email == null || email.isEmpty()){
            return true;
        }

        String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher  matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
