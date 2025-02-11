package br.com.fiap.contatos.service;


import br.com.fiap.contatos.dto.contato_exibicao_dto;
import br.com.fiap.contatos.model.contato_model;
import br.com.fiap.contatos.repository.contato_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class contato_service {

    @Autowired //@autowired serve para instanciar a interface no service
    private contato_repository contatoRepository;

    public contato_exibicao_dto gravar(contato_model contato) {
        return new contato_exibicao_dto(contatoRepository.save(contato));
    }

    public contato_exibicao_dto buscarPorId(Long id) {
        Optional<contato_model> contatoModelOptional = contatoRepository.findById(id);
        if (contatoModelOptional.isPresent()) {
            return new contato_exibicao_dto(contatoModelOptional.get());
        } else {
            throw new RuntimeException("Contato não encontrado");
        }
    }

    public List<contato_exibicao_dto> ListarAllContatos() {
        return contatoRepository.findAll().stream().map(contato_exibicao_dto::new).toList();
        //.findall -> retorna uma lista <contatos> buscando todos as informações do bd;
        //.stream -> cria um fluxo de dados afim de transformar <contatos> em <contato_exibicao_dto> de maneira mais limpa, sem usar um forloop;
        //.map -> intera sobre cada dado. Ele que faz a transformação de <contato> em um novo objeto <contato_exibicao_dto> dentro do stream;
        //.tolist -> transforma os dados, agora organizados em objetos dto, em uma lista.
    }

    public void excluir(Long id) {
        Optional<contato_model> contatoModelOptional = contatoRepository.findById(id);
        if (contatoModelOptional.isPresent()) {
            contatoRepository.delete(contatoModelOptional.get());
        } else {
            throw new RuntimeException("Contato não encontrado");
        }
    }

    public List<contato_model> mostrarAniversariantes(LocalDate dataInicial, LocalDate dataFinal) {
        return contatoRepository.findByDataNascimentoBetween(dataInicial, dataFinal);
    }

    public contato_model atualizar(contato_model contato) {
        Optional<contato_model> optionalContatoModel = contatoRepository.findById(contato.getId());
        if (optionalContatoModel.isPresent()) {
            return contatoRepository.save(contato);
        } else {
            throw new RuntimeException("Contato não encontrado.");
        }
    }

    public contato_model buscarPorNome(String nome) {
        Optional<contato_model> optionalContatoModel = contatoRepository.findByNome(nome);
        if (optionalContatoModel.isPresent()) {
            return optionalContatoModel.get();
        } else {
            throw new RuntimeException("Nome não encontrado.");
        }
    }

}
