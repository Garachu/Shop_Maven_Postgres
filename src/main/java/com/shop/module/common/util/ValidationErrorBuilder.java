package com.shop.module.common.util;

import com.shop.module.common.domain.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Created by meg on 7/21/17.
 */

@Slf4j
public class ValidationErrorBuilder {

    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed with " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
            log.error("Error: " + objectError.getDefaultMessage());
        }
        return error;
    }
}
