package br.com.bytebank.model;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ContaCorrenteTest {

    @Test
    void deveSacarComTaxaQuandoSaldoSuficiente() {
        ContaCorrente conta = new ContaCorrente(null, 1, new BigDecimal("100.00"));

        boolean sucesso = conta.sacar(new BigDecimal("50.00"));

        assertTrue(sucesso);
        BigDecimal valorEsperado = new BigDecimal("49.80");

        assertEquals(valorEsperado, conta.getSaldo());
    }

    @Test
    void naoDeveSacarQuandoSaldoInsuficiente() {
        ContaCorrente conta = new ContaCorrente(null, 1, new BigDecimal("50.00"));

        boolean sucesso = conta.sacar(new BigDecimal("49.90"));

        assertFalse(sucesso);
        assertEquals(0, new BigDecimal("50.00").compareTo(conta.getSaldo()));
    }

    @Test
    void deveTransferirValorParaOutraConta() {
        ContaCorrente origem = new ContaCorrente(null, 1, new BigDecimal("100.00"));
        ContaPoupanca destino = new ContaPoupanca(null, 2, new BigDecimal("10.00"));

        boolean sucesso = origem.transferir(new BigDecimal("50"), destino);

        assertTrue(sucesso);
        assertEquals(new BigDecimal("49.80"), origem.getSaldo());
        assertEquals(0, new BigDecimal("60.00").compareTo(destino.getSaldo()));
    }
}
