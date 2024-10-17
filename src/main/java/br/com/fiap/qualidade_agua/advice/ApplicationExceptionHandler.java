package br.com.fiap.qualidade_agua.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manusearArgunmentosInvalidos(MethodArgumentNotValidException erro) {
        Map<String, String> erros = new HashMap<>();
        List<FieldError> fieldErrors = erro.getBindingResult().getFieldErrors();

        for(FieldError fieldError : fieldErrors) {
           erros.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return erros;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> manusearIntegridadeDosDados() {
        Map<String, String> erros = new HashMap<>();
        erros.put("erro", "E-mail já cadastrado");

        return erros;
    }
}
