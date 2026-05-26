package br.com.bytebank.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransacaoRequest(
        @NotNull
        @Positive
        double valor

) {
}
