package br.com.bytebank.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Entity
public class ContaPoupanca extends Conta {


    public ContaPoupanca(Cliente titular, int numero, BigDecimal saldo) {
        super(saldo, numero, titular);
    }
    //METODO DE SAQUE SEM JUROS
    @Override
    public boolean sacar(BigDecimal valor) {
       if (this.saldo.compareTo(valor) >= 0){
           this.saldo = this.saldo.subtract(valor);
           return true;
       }else {
           return false;
       }
    }
}
