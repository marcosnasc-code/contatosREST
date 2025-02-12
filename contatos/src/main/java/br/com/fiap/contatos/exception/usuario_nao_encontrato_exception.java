package br.com.fiap.contatos.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class usuario_nao_encontrato_exception extends RuntimeException {

    public usuario_nao_encontrato_exception(String message){
        super(message);
        //Chamando o construtor da RunTimeException
    }

}
