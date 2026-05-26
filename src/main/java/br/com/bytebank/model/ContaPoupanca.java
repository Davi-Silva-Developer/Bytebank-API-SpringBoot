package br.com.bytebank.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class ContaPoupanca extends Conta {


    public ContaPoupanca(Cliente titular, int numero, double saldo) {
        super(saldo, numero, titular);
    }
    //METODO DE SAQUE SEM JUROS
    @Override
    public boolean sacar(double valor) {
       if (this.saldo >= valor){
           this.saldo -= valor;
           return true;
       }else {
           return false;
       }
    }
}
