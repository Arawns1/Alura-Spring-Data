package br.com.alura.springdata.service;

import br.com.alura.springdata.domain.Cargo;
import br.com.alura.springdata.domain.Funcionario;
import br.com.alura.springdata.dto.FuncionarioDTO;
import br.com.alura.springdata.dto.SaveFuncionarioDTO;
import br.com.alura.springdata.projections.FuncionarioProjection;
import br.com.alura.springdata.repository.CargoRepository;
import br.com.alura.springdata.repository.FuncionarioRepository;
import br.com.alura.springdata.specification.SpecificationFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private CargoRepository cargoRepository;

    public List<FuncionarioProjection> findFuncionarioByIdNomeSalario(){
        return repository.findFuncionarioSalario();
    }

    public List<FuncionarioDTO> findFuncionarioByConsultaDinamica(String nome, String salario){
        Specification<Funcionario> spec = Specification.where(SpecificationFuncionario.nome(nome)
                                                       .and(SpecificationFuncionario.salario(Double.parseDouble(salario))));
        List<Funcionario> funcionarioList = repository.findAll(spec);
        return funcionarioList.stream().map(FuncionarioDTO::new).toList();
    }

    public List<FuncionarioDTO> findAllByNome(String nome){
        return repository.findAllByNomeContainsAndStatusTrue(nome).stream().map(FuncionarioDTO::new).toList();
    }

    public List<FuncionarioDTO> findAllFuncionario() {
        return repository.findAllByStatusTrue().stream().map(FuncionarioDTO::new).toList();
    }

    public FuncionarioDTO findFuncionarioById(UUID uuid) {
        return new FuncionarioDTO(repository.getReferenceById(uuid));
    }

    public FuncionarioDTO saveFuncionario(SaveFuncionarioDTO dados) {
        Cargo cargo = cargoRepository.getReferenceById(dados.cargoId());
        Funcionario funcionario = new Funcionario(dados);
        funcionario.setCargo(cargo);
        return new FuncionarioDTO(repository.save(funcionario));
    }

    public FuncionarioDTO updateFuncionario(FuncionarioDTO dados) {
        Cargo cargo = cargoRepository.getReferenceById(dados.cargo().id());
        Funcionario funcionario = repository.getReferenceById(dados.id());
        funcionario.setCargo(cargo);
        funcionario.setCPF(dados.CPF());
        funcionario.setNome(dados.nome());
        funcionario.setId(dados.id());
        funcionario.setSalario(dados.salario());
        funcionario.setDataContratacao(dados.dataContratacao());
        return new FuncionarioDTO(repository.save(funcionario));
    }

    public void deleteFuncionario(UUID id) {
        Funcionario funcionario = repository.getReferenceById(id);
        funcionario.setStatus(false);

    }

}
