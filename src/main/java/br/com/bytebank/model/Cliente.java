package br.com.bytebank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;



@Entity
@Data
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //ID automatico

    public Cliente() {}

    private String nome;

    private String cpf;

    private String profissao;




}
