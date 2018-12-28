package ir.app_service.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public abstract class Validator {

    protected List<String> resolveBindingResultMessages(BindingResult result) {
        List<String> errorMessages = new ArrayList<>();
        if (result.hasErrors()) {
            for (Object object : result.getAllErrors()) {
                if (object instanceof FieldError) {
                    FieldError fieldError = (FieldError) object;
                    errorMessages.add(fieldError.getDefaultMessage());
                } else if (object instanceof ObjectError) {
                    ObjectError fieldError = (ObjectError) object;
                    errorMessages.add(fieldError.getDefaultMessage());
                }
            }
        }
        return errorMessages;
    }
}
