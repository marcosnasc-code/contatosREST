package br.com.fiap.contatos.repository;

import br.com.fiap.contatos.model.contato_model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface contato_repository extends JpaRepository<contato_model, Long> {

    public Optional<contato_model> findByNome(String nome);

    public List<contato_model> findByDataNascimentoBetween(LocalDate dataInicial, LocalDate dataFinal);

} //Rever conceitos da classe contato_repository + contatos_service;
