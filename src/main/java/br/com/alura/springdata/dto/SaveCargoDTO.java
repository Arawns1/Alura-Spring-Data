package br.com.alura.springdata.dto;

import br.com.alura.springdata.domain.Cargo;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record SaveCargoDTO(@NotBlank String descricao) {
}
