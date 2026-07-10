package br.com.bytebank.service;

import br.com.bytebank.model.ContaCorrente;
import br.com.bytebank.repository.ContaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {


    @Mock
    private ContaRepository repository;

    @InjectMocks
    private ContaService service;

    @Test
    @DisplayName("Deve realizar depósito com sucesso e atualizar o saldo")
    void deveDepositarComSucesso(){

        //Preparando o cenário
        ContaCorrente contaFake = new ContaCorrente();
        contaFake.setId(1L);
        contaFake.setSaldo(500.00);

        when(repository.findById(1L)).thenReturn(Optional.of(contaFake));

        //Dispararando o método real
        service.depositar(1L, 50.00);

        assertEquals(550.00, contaFake.getSaldo());

        verify(repository).save(contaFake);


    }


    @Test
    @DisplayName("Não deve permitir depósito em uma conta que não existe")
    void naoDeveDepositarEmContaInexistente(){

        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->{
            service.depositar(99L, 50.00);
        });
    }




    @Test
    @DisplayName("Deve realizar saque com sucesso quando houver saldo suficiente")
    void deveSacarComSucesso() {
        // ARRANGE (Preparar)
        ContaCorrente contaFake = new ContaCorrente();
        contaFake.setId(1L);
        contaFake.setSaldo(100.0);

        when(repository.findById(1L)).thenReturn(Optional.of(contaFake));

        // ACT (Tenta sacar 30 de uma conta que tem 100)
        service.sacar(1L, 30.0);

        //ASSERT (Verificar)
        // O saldo tem que cair para 69.80)
        assertEquals(69.80, contaFake.getSaldo());
        verify(repository).save(contaFake);
    }

    @Test
    @DisplayName("Não deve permitir saque quando o saldo for insuficiente")
    void naoDeveSacarComSaldoInsuficiente() {
        // AAA: ARRANGE (Preparar)
        ContaCorrente contaFake = new ContaCorrente();
        contaFake.setId(1L);
        contaFake.setSaldo(10.0); //O cliente tem R$10,00 na conta

        when(repository.findById(1L)).thenReturn(Optional.of(contaFake));

        //ACT & ASSERT (Agir e Verificar)
        // Tenta sacar 50 tendo 10. A Service TEM que explodir uma exceção!
        assertThrows(RuntimeException.class, () -> {
            service.sacar(1L, 50.0);
        });
    }




}
