package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.dto.usuario_cadastro_dto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class auth_controller {

    @Autowired
    private AuthenticationManager authenticationManager;
    //Criando um ponto para um manejador de autenticação

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid usuario_cadastro_dto usuarioCadastroDto){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                usuarioCadastroDto.email(), usuarioCadastroDto.senha());
        //recebendo o email e senha do usuario pelo link e criando um novo objeto tipo UsernamePassword

        Authentication auth = authenticationManager.authenticate(usernamePassword);
        //usando o objeto criado para fazer a autenticacao com o manejador criado anteriormente;

        return ResponseEntity.ok().build();
        //se estiver tudo okay o metodo retorna o status de ok.

    }

}
