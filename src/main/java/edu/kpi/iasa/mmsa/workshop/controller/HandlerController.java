package edu.kpi.iasa.mmsa.workshop.controller;

import edu.kpi.iasa.mmsa.workshop.exception.StatusNotFoundException;
import edu.kpi.iasa.mmsa.workshop.model.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

@ControllerAdvice
public class HandlerController {

    @ExceptionHandler(value
            = { StatusNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            StatusNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Status Not Found").build();
        return ResponseEntity.of(Optional.of(error)).notFound().build();
    }
}
