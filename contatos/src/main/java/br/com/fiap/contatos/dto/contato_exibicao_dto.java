package br.com.fiap.contatos.dto;

import br.com.fiap.contatos.model.contato_model;

import java.time.LocalDate;

//classes record são ideais para representar dados, não sendo necessários getters e setters por exemplo;
public record contato_exibicao_dto(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento
) {

    public contato_exibicao_dto(contato_model contato) {
        this(
                contato.getId(),
                contato.getNome(),
                contato.getEmail(),
                contato.getDataNascimento()
        );
    }

}
