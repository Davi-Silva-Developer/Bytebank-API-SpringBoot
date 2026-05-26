package br.com.bytebank.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente titular, int numero, double saldo) {
        super(saldo,numero,titular);
    }
    // METODO DE SAQUE APLICANDO OS JUROS
    @Override
    public boolean sacar(double valor) {
        if (this.saldo >= valor + 0.20){
            this.saldo -= (valor + 0.20);
            return true;
        }else {
            return false;
        }
    }
}
