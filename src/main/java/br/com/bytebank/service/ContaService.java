package br.com.bytebank.service;


import br.com.bytebank.model.Conta;
import br.com.bytebank.repository.ContaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ContaService {


    private final ContaRepository contaRepository;


    @Transactional
    public  void depositar(Long id, BigDecimal valor){
        Conta conta = contaRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("Conta não encontrada!!"));
        conta.depositar(valor);
        contaRepository.save(conta);

    }


    @Transactional
    public  void sacar(Long id, BigDecimal valor){

        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conta não foi encontrada"));

        boolean sucesso = conta.sacar(valor);

        if(!sucesso){
            throw new IllegalArgumentException("Saldo insuficiente para realizar o saque.");
        }
        contaRepository.save(conta);

    }

    @Transactional
    public void transferir(Long idOrigem, Long idDestino, BigDecimal valor){

        Conta contaOrigem = contaRepository.findById(idOrigem)
                .orElseThrow( ()-> new IllegalArgumentException("Conta não encontrada!!"));

        Conta contaDestino = contaRepository.findById(idDestino)
                .orElseThrow(() -> new IllegalArgumentException("Endereço de destino não encontrado"));

        boolean sucesso = contaOrigem.transferir(valor, contaDestino);

        if(!sucesso){
            throw new IllegalArgumentException("Saldo insuficiente...");
        }


        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);


    }







}
