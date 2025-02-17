package br.com.fiap.contatos.repository;

import br.com.fiap.contatos.model.usuario_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface usuario_repository extends JpaRepository<usuario_model, Long> {

    //UserDetails findByEmail (String email);
    Optional<usuario_model> findByEmail (String email);

}
