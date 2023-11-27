package br.com.alura.springdata.projections;

import java.util.UUID;

public interface FuncionarioProjection {
    UUID getId();
    String getNome();
    double getSalario();
}
