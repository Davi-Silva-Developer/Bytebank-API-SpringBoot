package br.com.bytebank.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransacaoRequest(
        @NotNull
        @Positive
        BigDecimal valor

) {
}
