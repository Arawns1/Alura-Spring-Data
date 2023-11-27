package br.com.alura.springdata.repository;

import br.com.alura.springdata.domain.Funcionario;
import br.com.alura.springdata.projections.FuncionarioProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    List<Funcionario> findAllByStatusTrue();
    List<Funcionario> findAllByNomeContainsAndStatusTrue(String nome);

    @Query(value = "select f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
    List<FuncionarioProjection> findFuncionarioSalario();
}
