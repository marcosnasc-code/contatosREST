package br.com.fiap.contatos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record contato_cadastro_dto(
        Long id,

        @NotBlank(message = "Nome do contato precisa ser inserido!") //bean validation para validação
        // dos dados inseridos pelo usuario
        String nome,

        @NotBlank(message = "Email do contato precisa ser inserido!")
        @Email(message = "Insira um e-mail com o formato correto!")
        String email,

        @NotBlank(message = "Senha é obrigatória!")
        @Size(min = 6, max = 10, message = "A senha deve conter entre 6 e 10 caracteres!")
        String senha,

        @NotNull(message = "Data de nascimento é obrigatória!")
        LocalDate dataNascimento
) {
}
