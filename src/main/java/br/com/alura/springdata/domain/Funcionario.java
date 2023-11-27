package br.com.alura.springdata.domain;

import br.com.alura.springdata.dto.FuncionarioDTO;
import br.com.alura.springdata.dto.SaveFuncionarioDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity(name = "Funcionario")
@Table(name = "funcionarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private boolean status;

    private String nome;

    private String CPF;

    private double salario;

    private LocalDate dataContratacao;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "funcionarios_unidades",
               joinColumns = {@JoinColumn(name = "fk_funcionario")},
               inverseJoinColumns = {@JoinColumn(name = "fk_unidade")})

    private List<Unidade> unidades;

    public Funcionario(FuncionarioDTO dados) {
        this.id = dados.id();
        this.status = true;
        this.nome = dados.nome();
        this.CPF = dados.CPF();
        this.salario = dados.salario();
        this.dataContratacao = dados.dataContratacao();

    }

    public Funcionario(SaveFuncionarioDTO dados) {
        this.status = true;
        this.nome = dados.nome();
        this.CPF = dados.CPF();
        this.salario = dados.salario();
        this.dataContratacao = dados.dataContratacao();
    }
}
