package br.com.fiap.contatos.controller;


import br.com.fiap.contatos.dto.contato_cadastro_dto;
import br.com.fiap.contatos.dto.contato_exibicao_dto;
import br.com.fiap.contatos.model.contato_model;
import br.com.fiap.contatos.service.contato_service;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public List<contato_exibicao_dto> listarAllContatos(){
        return contato_service.ListarAllContatos();
    }

    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)//PathVariable faz com que o id passado como parametro seja usado no link para identificar qual contato excluir; isso deixa a função mais dinâmica, podendo excluir qualquer id inserido do lado do usuário;
    public void excluir(@PathVariable Long id){
        contato_service.excluir(id);
    }

    //
    // - FAZER O BEAN VALIDATION AQUI TAMBÉM -
    //
    @PutMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public contato_model atualizar(@RequestBody contato_model contato){
        return contato_service.atualizar(contato);
    }
    //
    //
    //

    @GetMapping("contatos/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public contato_model buscarPorNome(@PathVariable String nome){
        return contato_service.buscarPorNome(nome);
    }

    @GetMapping("contatos/{dataInicial}/{dataFinal}")
    @ResponseStatus(HttpStatus.OK)
    public List<contato_model> mostrarAniversario (@PathVariable LocalDate dataInicial, @PathVariable LocalDate dataFinal){
        return contato_service.mostrarAniversariantes(dataInicial, dataFinal);
    }

    @GetMapping("contatos/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public contato_exibicao_dto buscarPorId(@PathVariable Long id){
        return contato_service.buscarPorId(id);
    }


}
