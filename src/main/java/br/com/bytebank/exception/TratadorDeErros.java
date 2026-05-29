package br.com.bytebank.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> TratarErroDeRegraDenegocio(IllegalArgumentException ex){

        //retorna BadRequest com a exata mensagem do service
        return ResponseEntity.badRequest().body("⚠ Erro na operação:" + ex.getMessage());
    }


    //Retorna um badRequest com a mensagem do DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> tratarErroDeValidacao(MethodArgumentNotValidException ex){
        List<String> erros = ex.getFieldErrors().stream()
                .map(erro -> "Campo '" + erro.getField() + "': " + erro.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(erros);
    }

}

