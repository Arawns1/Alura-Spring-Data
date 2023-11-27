package br.com.alura.springdata.dto;

import br.com.alura.springdata.domain.Cargo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CargoDTO(@NotNull UUID id, @NotBlank String descricao) {
    public CargoDTO(Cargo cargo) {
        this(cargo.getId(), cargo.getDescricao());
    }
}
