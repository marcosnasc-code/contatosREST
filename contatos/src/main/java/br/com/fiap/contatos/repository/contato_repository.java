package br.com.fiap.contatos.repository;

import br.com.fiap.contatos.model.contato_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface contato_repository extends JpaRepository<contato_model, Long> {

    @Query("SELECT c FROM contato_model c WHERE c.nome = :nome")
    Optional<contato_model> buscarPeloNome(@Param("nome") String nome);

    // Consulta por metodo
    Optional<contato_model> findByEmail(String email);

    @Query("SELECT c FROM contato_model c WHERE c.dataNascimento BETWEEN :dataInicial AND :dataFinal")
    List<contato_model> listarAniversariantes(@Param("dataInicial") LocalDate dataInicial, @Param("dataFinal") LocalDate dataFinal);



}
