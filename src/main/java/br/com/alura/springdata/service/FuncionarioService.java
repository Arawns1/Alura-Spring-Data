package br.com.alura.springdata.service;

import br.com.alura.springdata.domain.Cargo;
import br.com.alura.springdata.domain.Funcionario;
import br.com.alura.springdata.dto.FuncionarioDTO;
import br.com.alura.springdata.dto.SaveFuncionarioDTO;
import br.com.alura.springdata.repository.CargoRepository;
import br.com.alura.springdata.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private CargoRepository cargoRepository;

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
