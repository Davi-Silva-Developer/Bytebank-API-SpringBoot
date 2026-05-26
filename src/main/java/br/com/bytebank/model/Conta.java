package br.com.bytebank.model;


import jakarta.persistence.*;
import lombok.*;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected double saldo;
    private int numero;
    public Conta(double saldo,int numero, Cliente Titular){
        this.saldo = saldo;
        this.numero = numero;
        this.titular = Titular;
    }




    @ManyToOne
    @JoinColumn(name =  "cliente_id")
    private Cliente titular;  // COMPOSIÇÃO: Muitas contas tem um cliente.










    //---DEPOSITAR VALOR
    public void depositar(double valor){
        this.saldo += valor;
    }

    //--- TRANFERIR VALOR -----
    public boolean transferir(double valor, Conta destino){
        if (this.sacar(valor)) {

            destino.depositar(valor);
            return true;
        }
        return false;



    }


    //verificando se existe saldo para transferir para a outra conta
    public abstract boolean sacar(double valor);









}
