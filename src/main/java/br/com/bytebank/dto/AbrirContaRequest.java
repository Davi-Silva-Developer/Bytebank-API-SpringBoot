package br.com.bytebank.dto;


import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;

public record AbrirContaRequest(

        @Nonnull Long clienteId,
        @Nonnull int numero,

        @Nonnull @Positive double saldoInicial,


        @Nonnull @Pattern(regexp = "CC|CP", message = "O tipo de conta deve ser CC (Corrente) ou CP (Poupança)") //diferenciando dentro da tabela conta
        String tipoDeConta


) {}
