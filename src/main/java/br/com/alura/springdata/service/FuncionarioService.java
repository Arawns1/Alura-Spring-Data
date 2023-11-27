package br.com.alura.springdata.service;

import br.com.alura.springdata.domain.Funcionario;
import br.com.alura.springdata.dto.FuncionarioDTO;
import br.com.alura.springdata.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public List<Funcionario> findAllFuncionario(){
        return repository.findAll();
    }
    public Funcionario findFuncionarioById(UUID uuid){
        return repository.getReferenceById(uuid);
    }

    public FuncionarioDTO saveFuncionario(FuncionarioDTO dados){
        repository.save(new Funcionario(dados));
        return dados;
    }

    public FuncionarioDTO updateFuncionario(FuncionarioDTO dados){
        Optional<Funcionario> funcionario = repository.findById(dados.id());

        if(funcionario.isPresent()){
            repository.save(new Funcionario(dados));
            return dados;
        }
        else{
            throw new RuntimeException("Funcionário with id "+dados.id()+" not found!");
        }

    }

    public void deleteFuncionario(FuncionarioDTO dados){
        Optional<Funcionario> funcionario = repository.findById(dados.id());

        if(funcionario.isPresent()){
            funcionario.get().setStatus(false);
        }
        else{
            throw new RuntimeException("Funcionário with id "+dados.id()+" not found!");
        }
    }

}
