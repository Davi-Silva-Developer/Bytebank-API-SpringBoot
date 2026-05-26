package br.com.bytebank.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransferenciaRequest(

        @NotNull
        @Positive
        double valor,

        @NotNull
        Long contaDestinoId

) { }
