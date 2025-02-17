package br.com.fiap.contatos.service;


import br.com.fiap.contatos.dto.usuario_cadastro_dto;
import br.com.fiap.contatos.dto.usuario_exibicao_dto;
import br.com.fiap.contatos.exception.usuario_nao_encontrato_exception;
import br.com.fiap.contatos.model.usuario_model;
import br.com.fiap.contatos.repository.usuario_repository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class usuario_service {

    @Autowired //instanciar o usuario repository na classe service
    private usuario_repository usuarioRepository;

    public usuario_exibicao_dto gravar(usuario_cadastro_dto usuarioCadastroDto){
        usuario_model usuario = new usuario_model();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);
        //CopyProperties vai fazer a cópia de dados do usuariocadastrodto para a variável usuario

        return new usuario_exibicao_dto(usuarioRepository.save(usuario));
    }

    public usuario_exibicao_dto buscarPorId(Long id){
        Optional<usuario_model> usuarioModelOptional = usuarioRepository.findById(id);
        if (usuarioModelOptional.isPresent()){
            return new usuario_exibicao_dto(usuarioModelOptional.get());
        }else {
            throw new usuario_nao_encontrato_exception("Usuário não encontrado!");
        }
    }

    public Page<usuario_exibicao_dto> listarAllUsuarios (Pageable paginacao){
        return usuarioRepository.findAll(paginacao)
                .map(usuario_exibicao_dto::new);
    }

    public void excluir(Long id) {
        Optional<usuario_model> usuarioModelOptional = usuarioRepository.findById(id);
        if(usuarioModelOptional.isPresent()){
            usuarioRepository.delete(usuarioModelOptional.get());
        }else {
            throw new RuntimeException("Contato não encontrado");
        }
    }

    //Atualizar -- Não verificado completamente -- Tarefas
    public usuario_model atualizar(usuario_model usuario){
        Optional<usuario_model> optionalUsuarioModel = usuarioRepository.findByEmail(usuario.getEmail());
        if (optionalUsuarioModel.isPresent()){
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Contato não encontrado");
        }
    }
    //Atualizar -- Não verificado completamente -- Tarefas
}
