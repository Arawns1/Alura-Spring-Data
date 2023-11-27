package br.com.alura.springdata.dto;

import br.com.alura.springdata.domain.Cargo;
import br.com.alura.springdata.domain.Unidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UnidadeDTO(@NotNull UUID id, @NotBlank String descricao, @NotBlank String endereco) {

    public UnidadeDTO(Unidade unidade) {
        this(unidade.getId(), unidade.getDescricao(), unidade.getEndereco());
    }
}
