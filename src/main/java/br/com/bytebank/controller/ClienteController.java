package br.com.bytebank.controller;

import br.com.bytebank.model.Cliente;
import br.com.bytebank.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClienteController {

    @RestController
    @RequestMapping("/api/clientes")
    public class clienteController {

        @Autowired
        private ClienteRepository repository;
        // Rota para LISTAR todos os clientes
        @GetMapping
        public List<Cliente> listar(){
            return repository.findAll();
        }

        // Rota para CRIAR um novo cliente
        @PostMapping
        public Cliente criar(@RequestBody Cliente novoCliente){
            System.out.println("NOME RECEBIDO: " + novoCliente.getNome());
            System.out.println("CPF RECEBIDO: " + novoCliente.getCpf());
            repository.save(novoCliente);

            return novoCliente;
        }









    }
}
