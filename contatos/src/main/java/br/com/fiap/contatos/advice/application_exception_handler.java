package br.com.fiap.contatos.advice;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Classe para tratar exceções em escopo global
@RestControllerAdvice //indica para o rest que essa classe vai tratar qualquer exceção que ocorra na aplicação
public class application_exception_handler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> manusearArgumentosInvalidos(MethodArgumentNotValidException erro){
        Map<String, String> mapaDeErro = new HashMap<>();
        List<FieldError> camposDeErro = erro.getBindingResult().getFieldErrors(); //pegando cada linha do erro gerado(em json);

        for (FieldError campo : camposDeErro){
            mapaDeErro.put(campo.getField(), campo.getDefaultMessage());
            //Dentre os camposDeErro registrados, seleciona os Field + DefaultMessage pra formar um map<chave, valor>
            //pra ser retornado como um mapaDeErro;
        }

        return mapaDeErro;
        //Retorna o mapaDeErro formado no forloop;
    }

    //
    // - CASO APAREÇA NOVOS TIPO DE ERROS GLOBAIS COMO O MethodArgumentNotValidException, IMPLEMENTA-LOS AQUI
    //


}
