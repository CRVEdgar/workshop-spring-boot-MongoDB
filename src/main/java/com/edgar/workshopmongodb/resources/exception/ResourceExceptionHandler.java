package com.edgar.workshopmongodb.resources.exception;

import com.edgar.workshopmongodb.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    //classe responsavel por receber uma excecao e retornar uma informacao na tela no formato da classe ~StandardError~

    //quando estourar uma excecao do tipo ~ObjectNotFoundException~, vai ser gerado um objeto ~StandardError~, o qual sera retornado
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException exception, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND; //para objeto nao encontrado
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Nao Encontrado", exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

}
