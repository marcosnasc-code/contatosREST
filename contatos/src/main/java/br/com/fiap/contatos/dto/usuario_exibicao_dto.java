package br.com.fiap.contatos.dto;

import br.com.fiap.contatos.model.usuario_model;

public record usuario_exibicao_dto(
        Long usuarioId,
        String nome,
        String email
) {

    public usuario_exibicao_dto(usuario_model usuario){
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
