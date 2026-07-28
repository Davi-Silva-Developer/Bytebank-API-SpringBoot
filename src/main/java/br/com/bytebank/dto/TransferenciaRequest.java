package br.com.bytebank.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferenciaRequest(

        @NotNull
        @Positive
        BigDecimal valor,

        @NotNull
        Long contaDestinoId

) { }
