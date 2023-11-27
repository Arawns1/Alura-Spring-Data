package br.com.alura.springdata.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record SaveUnidadeDTO(@NotBlank String descricao, @NotBlank String endereco) {
}
