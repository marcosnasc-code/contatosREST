package br.com.fiap.contatos.controller;

import br.com.fiap.contatos.config.security.token_service;
import br.com.fiap.contatos.dto.login_dto;
import br.com.fiap.contatos.dto.token_dto;
import br.com.fiap.contatos.dto.usuario_cadastro_dto;
import br.com.fiap.contatos.dto.usuario_exibicao_dto;
import br.com.fiap.contatos.model.usuario_model;
import br.com.fiap.contatos.service.usuario_service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private usuario_service usuarioService;

    @Autowired
    private token_service tokenService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid login_dto loginDto){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                loginDto.email(), loginDto.senha());
        //recebendo o email e senha do usuario pelo link e criando um novo objeto tipo UsernamePassword

        Authentication auth = authenticationManager.authenticate(usernamePassword);
        //usando o objeto criado para fazer a autenticacao com o manejador criado anteriormente;
        String token = tokenService.gerarToken((usuario_model) auth.getPrincipal());
        //var token recebe o token gerado por gerarToken que está recebendo como parametro
        //(usuario_model) auth.getPrincipal() -> auth.getprincipal pega os atributos do usuario
        //(usuario_model) -> transforma auth em um objeto tipo usuario_model

        return ResponseEntity.ok(new token_dto(token));
        //se estiver tudo okay o metodo retorna o status de ok junto com o token.
    }

    @PostMapping("/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public usuario_exibicao_dto registrar(@RequestBody @Valid usuario_cadastro_dto usuarioCadastroDto){
        usuario_exibicao_dto usuarioSalvo = null;
        usuarioSalvo = usuarioService.gravar(usuarioCadastroDto);
        return usuarioSalvo;
    }

}
