package br.com.fiap.contatos.service;


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

    public contato_model gravar(contato_model contato){
        return contatoRepository.save(contato);
    }

    public contato_model buscarPorId(Long id){
        Optional<contato_model> contatoModelOptional = contatoRepository.findById(id);
        if (contatoModelOptional.isPresent()){
            return contatoModelOptional.get();
        } else {
            throw new RuntimeException("Contato não encontrado");
        }
    }

    public List<contato_model> ListarAllContatos(){
        return contatoRepository.findAll();
    }

    public void excluir (Long id){
        Optional<contato_model> contatoModelOptional = contatoRepository.findById(id);
        if (contatoModelOptional.isPresent()){
            contatoRepository.delete(contatoModelOptional.get());
        }else {
            throw new RuntimeException("Contato não encontrado");
        }
    }

    public List<contato_model> mostrarAniversariantes (LocalDate dataInicial, LocalDate dataFinal){
        return contatoRepository.findByDataNascimentoBetween(dataInicial, dataFinal);
    }

    public contato_model atualizar (contato_model contato){
        Optional<contato_model> optionalContatoModel = contatoRepository.findById(contato.getId());
        if (optionalContatoModel.isPresent()){
            return contatoRepository.save(contato);
        }else {
            throw new RuntimeException("Contato não encontrado.");
        }
    }

    public contato_model buscarPorNome (String nome){
        Optional<contato_model> optionalContatoModel = contatoRepository.findByNome(nome);
        if (optionalContatoModel.isPresent()){
            return optionalContatoModel.get();
        }else {
            throw new RuntimeException("Nome não encontrado.");
        }
    }

}
