package br.com.fiap.contatos.controller;


import br.com.fiap.contatos.dto.contato_cadastro_dto;
import br.com.fiap.contatos.dto.contato_exibicao_dto;
import br.com.fiap.contatos.model.contato_model;
import br.com.fiap.contatos.service.contato_service;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class contato_controller {

    @Autowired
    private contato_service contato_service;

    @PostMapping("/contatos")
    @ResponseStatus(HttpStatus.CREATED)
    public contato_exibicao_dto gravar (@RequestBody @Valid contato_cadastro_dto contatoCadastroDto){
        //Quando se faz um @PostMapping é preciso informar qual o 'contatoDto' a ser gravado no BD;
        //@RequestBody indica ao REST que um novo contatoDto será gravado e que ele está vindo a partir de uma requisição HTTP do tipo POST.
        //Ele deve buscar esse contato no corpo da requisição. Esse contato será enviado na forma de um texto (json) e será transformado em um objeto contato pelo REST

        //@Valid pertence ao Bean Validation, para o metodo validar os dados de entrada
        return contato_service.gravar(contatoCadastroDto);
    }

    //contatos?size={tamanho da pagina}&page={seleção/numero da pagina}&sort={atributo de ordenacao},{ASC ou DESC -optional-}
    @GetMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Page<contato_exibicao_dto> listarAllContatos(Pageable paginacao){
        return contato_service.ListarAllContatos(paginacao);
    }

    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)//PathVariable faz com que o id passado como parametro seja usado no link para identificar qual contato excluir; isso deixa a função mais dinâmica, podendo excluir qualquer id inserido do lado do usuário;
    public void excluir(@PathVariable Long id){
        contato_service.excluir(id);
    }

    @PutMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public contato_model atualizar(@RequestBody contato_model contato){
        return contato_service.atualizar(contato);
    }

    @GetMapping("contatos/{nome}")
    //@GetMapping(value = "/contatos", params = "nome")
    //link get mapping acima -> api/contatos?nome={nome} /// opção segundaria de link para caso tenha links duplicados
    @ResponseStatus(HttpStatus.OK)
    public contato_exibicao_dto buscarPorNome(@PathVariable String nome){
    //public contato_exibicao_dto buscarPorNome(@RequestParam String nome){
    //caso use a segunda forma de link
        return contato_service.buscarPorNome(nome);
    }

    //api/contatos?dataInicial={dataInicial}&dataFinal={dataFinal}
    @GetMapping(value = "/contatos", params = {"dataInicial", "dataFinal"})
    @ResponseStatus(HttpStatus.OK)
    public List<contato_exibicao_dto> mostrarAniversario (@RequestParam LocalDate dataInicial, @RequestParam LocalDate dataFinal){
        return contato_service.mostrarAniversariantes(dataInicial, dataFinal);
    }

    @GetMapping("contatos/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public contato_exibicao_dto buscarPorId(@PathVariable Long id){
        return contato_service.buscarPorId(id);
    }

    @GetMapping("contatos/email/{email}")
    public contato_exibicao_dto buscarPorEmail(@PathVariable String email){
        return contato_service.buscarPorEmail(email);
    }


}
