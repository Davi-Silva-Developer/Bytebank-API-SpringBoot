package br.com.bytebank.controller;

import br.com.bytebank.model.Cliente;
import br.com.bytebank.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClienteController {

    @RestController
    @RequestMapping("/api/CLientes")
    @RequiredArgsConstructor
    public class clienteController {

        private final ClienteRepository repository;
        // Rota para LISTAR todos os clientes
        @GetMapping
        public List<Cliente> listar(){
            return repository.findAll();
        }

        // Rota para CRIAR um novo cliente
        @PostMapping
        public Cliente criar(@RequestBody Cliente novoCliente){

            repository.save(novoCliente);

            return novoCliente;
        }

    }


}
