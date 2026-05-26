package br.com.bytebank.controller;

import br.com.bytebank.dto.AbrirContaRequest;
import br.com.bytebank.dto.TransacaoRequest;
import br.com.bytebank.dto.TransferenciaRequest;
import br.com.bytebank.model.Cliente;
import br.com.bytebank.model.Conta;
import br.com.bytebank.model.ContaCorrente;
import br.com.bytebank.model.ContaPoupanca;
import br.com.bytebank.repository.ClienteRepository;
import br.com.bytebank.repository.ContaRepository;
import br.com.bytebank.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//Criando conta
@RestController
@RequestMapping("/contas")
public class ContaController {


    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ContaService contaService;

    @Autowired
    private ClienteRepository clienteRepository;


    @PostMapping
    public String abrir(@RequestBody AbrirContaRequest dados) {
        //Procurando titular no banco de dados
        Cliente titular = clienteRepository.findById(dados.clienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        //Selecionando conta corrente ou conta poupança
        Conta novaConta;
        if (dados.tipoDeConta().equalsIgnoreCase("CC")) {
            novaConta = new ContaCorrente(titular, dados.numero(), dados.saldoInicial());


        } else {
            novaConta = new ContaPoupanca(titular, dados.numero(), dados.saldoInicial());
        }

        //salvando após validação
        contaRepository.save(novaConta);
        return "Conta " + dados.tipoDeConta() + " aberta com sucesso para " + titular.getNome();
    }



    @PutMapping("/{id}/depositar")
    public ResponseEntity<String> depositar(@PathVariable Long id, @RequestBody @Valid TransacaoRequest dados) {
        try {
            contaService.depositar(id, dados.valor());
            return ResponseEntity.ok("Depósito realizado com sucesso!!");


        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{id}/sacar")
    public ResponseEntity<String> sacar(@PathVariable Long id, @RequestBody @Valid TransacaoRequest dados) {

        try {
            contaService.sacar(id, dados.valor());
            return ResponseEntity.ok("Saque realizado com sucesso.");


        } catch (IllegalArgumentException e ) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/transferir")
    public ResponseEntity<String> tranferir(@PathVariable Long id, @RequestBody @Valid TransferenciaRequest dados){

        try{
            contaService.transferir(id, dados.contaDestinoId(), dados.valor());
            return ResponseEntity.ok("Valor transferido com sucesso!");


        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }



    }
}

