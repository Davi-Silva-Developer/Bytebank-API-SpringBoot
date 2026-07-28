package br.com.bytebank.model;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected BigDecimal saldo;
    private int numero;
    public Conta(BigDecimal saldo, int numero, Cliente Titular){
        this.saldo = saldo;
        this.numero = numero;
        this.titular = Titular;
    }




    @ManyToOne
    @JoinColumn(name =  "cliente_id")
    private Cliente titular;  // COMPOSIÇÃO: Muitas contas tem um cliente.










    //---DEPOSITAR VALOR
    public void depositar(BigDecimal valor){
        this.saldo = this.saldo.add(valor);
    }

    //--- TRANFERIR VALOR -----
    public boolean transferir(BigDecimal valor, Conta destino){
        if (this.sacar(valor)) {

            destino.depositar(valor);
            return true;
        }
        return false;



    }


    //verificando se existe saldo para transferir para a outra conta
    public abstract boolean sacar(BigDecimal valor);









}
