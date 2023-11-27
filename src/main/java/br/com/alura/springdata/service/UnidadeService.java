package br.com.alura.springdata.service;

import br.com.alura.springdata.domain.Unidade;
import br.com.alura.springdata.dto.SaveUnidadeDTO;
import br.com.alura.springdata.dto.UnidadeDTO;
import br.com.alura.springdata.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository repository;

    public List<UnidadeDTO> findAllUnidade(){
        return repository.findAllByStatusTrue().stream().map(UnidadeDTO::new).toList();
    }
    public UnidadeDTO findUnidadeById(UUID uuid){
        return new UnidadeDTO(repository.getReferenceById(uuid));
    }

    public UnidadeDTO saveUnidade(SaveUnidadeDTO dados){
        Unidade unidade = repository.save(new Unidade(dados));
        return new UnidadeDTO(unidade);
    }

    public UnidadeDTO updateUnidade(UnidadeDTO dados){
        repository.getReferenceById(dados.id());
        return new UnidadeDTO(repository.save(new Unidade(dados)));
    }

    public void deleteUnidade(UUID id){
        Unidade unidade = repository.getReferenceById(id);
        unidade.setStatus(false);
    }

}
