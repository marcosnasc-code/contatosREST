package br.com.fiap.contatos.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apidw")
public class helloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }
    @GetMapping("/ola")
    public String ola(){
        return "Olá mundo";
    }

}
