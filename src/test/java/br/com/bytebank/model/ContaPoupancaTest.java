package br.com.bytebank.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaPoupancaTest {

    @Test
    void deveSacarSemTaxaQuandoSaldoSuficiente() {
        ContaPoupanca conta = new ContaPoupanca(null, 1, new BigDecimal("100.0"));

        boolean sucesso = conta.sacar(new BigDecimal("50.0"));

        assertTrue(sucesso);
        assertEquals(new BigDecimal("50.0"), conta.getSaldo());
    }

    @Test
    void naoDeveSacarQuandoSaldoInsuficiente() {
        ContaPoupanca conta = new ContaPoupanca(null, 1, new BigDecimal("20.0"));

        boolean sucesso = conta.sacar(new BigDecimal("20.01"));

        assertFalse(sucesso);
        assertEquals(new BigDecimal("20.0"), conta.getSaldo());
    }
}
