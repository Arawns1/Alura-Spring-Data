package br.com.alura.springdata.domain;

import br.com.alura.springdata.dto.FuncionarioDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "funcionarios")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private boolean status;

    public Funcionario(FuncionarioDTO dados) {
        this.id = dados.id();
    }
}
