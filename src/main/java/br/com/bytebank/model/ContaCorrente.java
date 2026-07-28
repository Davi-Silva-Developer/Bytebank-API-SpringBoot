package br.com.bytebank.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Entity
public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente titular, int numero, BigDecimal saldo) {
        super(saldo,numero,titular);
    }
    @Override
    public boolean sacar(BigDecimal valor) {
        BigDecimal taxa = new BigDecimal("0.20");
        BigDecimal valorTotal = valor.add(taxa);

        if (this.saldo.compareTo(valorTotal) >= 0) {
            this.saldo = this.saldo.subtract(valorTotal);
            return true;
        } else {
            return false;
        }
    }
}
