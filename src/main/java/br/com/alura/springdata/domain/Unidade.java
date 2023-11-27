package br.com.alura.springdata.domain;

import br.com.alura.springdata.dto.SaveUnidadeDTO;
import br.com.alura.springdata.dto.UnidadeDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "unidades")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private boolean status;

    private String descricao;

    private String endereco;

    @ManyToMany(mappedBy = "unidades", fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios;

    public Unidade(SaveUnidadeDTO dados) {
        this.status = true;
        this.descricao = dados.descricao();
        this.endereco = dados.endereco();
    }

    public Unidade(UnidadeDTO dados) {
        this.id = dados.id();
        this.status = true;
        this.descricao = dados.descricao();
        this.endereco = dados.endereco();
    }
}
