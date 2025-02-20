package br.com.fiap.contatos.controller;


import br.com.fiap.contatos.dto.usuario_cadastro_dto;
import br.com.fiap.contatos.dto.usuario_exibicao_dto;
import br.com.fiap.contatos.model.usuario_model;
import br.com.fiap.contatos.service.usuario_service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class usuario_controller {

    @Autowired
    private usuario_service usuarioService;


    @PostMapping("/gravar")
    @ResponseStatus(HttpStatus.CREATED)
    public usuario_exibicao_dto gravar(@RequestBody @Valid usuario_cadastro_dto usuarioCadastroDto){
        return usuarioService.gravar(usuarioCadastroDto);
    }


    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public Page<usuario_exibicao_dto> listarAllUsuarios(Pageable pageable){
        return usuarioService.listarAllUsuarios(pageable);
    }

    @GetMapping("/listar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public usuario_exibicao_dto listarPorNome(@PathVariable Long id){
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public usuario_model atualizarUsuario (usuario_model usuario){
        return usuarioService.atualizar(usuario);
    }

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        usuarioService.excluir(id);
    }



    //


}
