package br.com.fiap.contatos.service;


import br.com.fiap.contatos.dto.contato_cadastro_dto;
import br.com.fiap.contatos.dto.contato_exibicao_dto;
import br.com.fiap.contatos.exception.usuario_nao_encontrato_exception;
import br.com.fiap.contatos.model.contato_model;
import br.com.fiap.contatos.repository.contato_repository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class contato_service {

    @Autowired //@autowired serve para instanciar a interface no service
    private contato_repository contatoRepository;

    //POSTERIORMENTE FAZER VALIDAÇÃO DE DADOS EM TODOS OS MÉTODOS

    public contato_exibicao_dto gravar(contato_cadastro_dto contatocadastrodto) {
        contato_model contato = new contato_model();
        BeanUtils.copyProperties(contatocadastrodto, contato);
        //BeanUtils é usada para copiar propriedades de um objeto para outro de forma automática.
        //Ela é útil para converter entidades em DTOs, fazer atualizações parciais e reduzir código repetitivo.
        //Ela copia valores de atributos com o mesmo nome e tipo do objeto origem para o destino.
        return new contato_exibicao_dto(contatoRepository.save(contato));
    }

    public contato_exibicao_dto buscarPorId(Long id) {
        Optional<contato_model> contatoModelOptional = contatoRepository.findById(id);
        if (contatoModelOptional.isPresent()) {
            return new contato_exibicao_dto(contatoModelOptional.get());
        } else {
            throw new usuario_nao_encontrato_exception("Contato não encontrado");
        }
    }

    //Listar todos os contatos vai estar paginado como exemplo
    //Tipo pageable <springframework.data.domain>
    public Page<contato_exibicao_dto> ListarAllContatos(Pageable paginacao) {
        return contatoRepository.findAll(paginacao)
                .map(contato_exibicao_dto::new);
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


    public List<contato_exibicao_dto> mostrarAniversariantes(LocalDate dataInicial, LocalDate dataFinal) {
        return contatoRepository.listarAniversariantes(dataInicial, dataFinal)
                .stream()
                .map(contato_exibicao_dto::new)
                .toList();
    }

    public contato_model atualizar(contato_model contato) {
        Optional<contato_model> optionalContatoModel = contatoRepository.findById(contato.getId());
        if (optionalContatoModel.isPresent()) {
            return contatoRepository.save(contato);
        } else {
            throw new RuntimeException("Contato não encontrado.");
        }
    }

    public contato_exibicao_dto buscarPorNome(String nome) {
        Optional<contato_model> optionalContatoModel = contatoRepository.buscarPeloNome(nome);
        if (optionalContatoModel.isPresent()) {
            return new contato_exibicao_dto(optionalContatoModel.get());
        } else {
            throw new usuario_nao_encontrato_exception("Nome não encontrado.");
        }
    }

    public contato_exibicao_dto buscarPorEmail(String email){
        Optional<contato_model> optionalContatoModel = contatoRepository.findByEmail(email);
        if (optionalContatoModel.isPresent()){
            return new contato_exibicao_dto(optionalContatoModel.get());
        }else {
            throw new usuario_nao_encontrato_exception("Email não encontrado!");
        }
    }

}
