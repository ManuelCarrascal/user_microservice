package emazon.user.infrastructure.configuration.exceptionhandler;

import emazon.user.domain.exception.*;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HandlerControllerAdvisor {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", errors));
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(EmailValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmailValidationException(EmailValidationException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(PhoneValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlePhoneValidationException(PhoneValidationException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(IdentityDocumentValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIdentityDocumentValidationException(IdentityDocumentValidationException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(AgeValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAgeValidationException(AgeValidationException ex) {
        return ex.getMessage();
    }


    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleAuthenticationException(AuthenticationException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidRoleException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleInvalidRoleException(InvalidRoleException ex) {
        return ex.getMessage();
    }
}

