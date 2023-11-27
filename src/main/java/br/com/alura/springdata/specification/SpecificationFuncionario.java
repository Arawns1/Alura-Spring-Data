package br.com.alura.springdata.specification;

import br.com.alura.springdata.domain.Funcionario;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class SpecificationFuncionario {

    public static Specification<Funcionario> nome(String nome){
        return ((root, query, criteriaBuilder) ->
            criteriaBuilder.like(
                    criteriaBuilder.upper(root.get("nome")),
                    "%" + nome.toUpperCase() + "%")
        );
    }
    public static Specification<Funcionario> salario(Double salario){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("salario"), salario)
        );
    }
    public static Specification<Funcionario> dataContratacao(LocalDate data){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("dataContratacao"), data)
        );
    }
}
