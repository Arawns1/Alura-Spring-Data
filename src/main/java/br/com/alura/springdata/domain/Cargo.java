package br.com.alura.springdata.domain;

import br.com.alura.springdata.dto.CargoDTO;
import br.com.alura.springdata.dto.SaveCargoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cargos")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private boolean status;

    private String descricao;

    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios;

    public Cargo(SaveCargoDTO dados) {
        this.status = true;
        this.descricao = dados.descricao();
    }

    public Cargo(CargoDTO dados) {
        this.id = dados.id();
        this.status = true;
        this.descricao = dados.descricao();
    }
}
