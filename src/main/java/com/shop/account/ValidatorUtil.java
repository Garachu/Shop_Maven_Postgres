package com.shop.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by meg on 8/17/17.
 */

@Service
@Scope("singleton")
@Slf4j
public class ValidatorUtil {

    private final ValidatorFactory factory;
    private final ObjectMapper mapper;

    {
        factory = Validation.buildDefaultValidatorFactory();
        mapper = new ObjectMapper();
    }

    public <T> ResponseEntity<?> validate(final T instance) {

        final Validator validator = factory.getValidator();

        final Set<ConstraintViolation<T>> violations = validator
                .validate(instance, Default.class);

        if (!violations.isEmpty()) {
            final Set<ConstraintViolation<?>> constraints =
                    new HashSet<>(violations.size());

            for (final ConstraintViolation<?> violation : violations) {
                constraints.add(violation);
            }
            StringBuilder sb = new StringBuilder("");
            for (ConstraintViolation<?> constraintViolation : constraints) {
                sb.append(constraintViolation.getMessage());
                break;
            }
            //throw new ConstraintViolationException( constraints );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("{\"msg\" : \"%s\"}", sb.toString()));
        }
        return null;
    }

    public Object validJson(String jsonString, Class c) {
        try {
            Object obj = mapper.readValue(jsonString, c);
            return obj;
        } catch (IOException ex) {
            //Logger.getLogger("validJson").log(Level.SEVERE, ex.getMessage());
            log.error("Validation: " + ex.getMessage());
            //return Response.status(Response.Status.BAD_REQUEST).entity("{\"msg\" : \"invalid json structure\"}").type(MediaType.APPLICATION_JSON).build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"msg\" : \"invalid json structure\"}");

        }
    }
}
