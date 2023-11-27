package br.com.alura.springdata.dto;

import br.com.alura.springdata.domain.Cargo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record SaveFuncionarioDTO(@NotBlank String nome,
                                 @NotBlank String CPF,
                                 @NotNull double salario,
                                 @NotNull LocalDate dataContratacao,
                                @NotNull UUID cargoId
) {
}
