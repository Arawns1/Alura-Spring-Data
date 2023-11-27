package br.com.alura.springdata.dto;

import br.com.alura.springdata.domain.Cargo;
import br.com.alura.springdata.domain.Funcionario;
import br.com.alura.springdata.domain.Unidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record FuncionarioDTO(@NotNull UUID id,
                             @NotBlank String nome,
                             @NotBlank String CPF,
                             @NotNull double salario,
                             @NotNull LocalDate dataContratacao,
                             CargoDTO cargo
                             ) {
    public FuncionarioDTO(Funcionario funcionario) {
        this(funcionario.getId(),
             funcionario.getNome(),
             funcionario.getCPF(),
             funcionario.getSalario(),
             funcionario.getDataContratacao(),
             new CargoDTO(funcionario.getCargo()));
    }
}
