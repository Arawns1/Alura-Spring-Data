package br.com.alura.springdata.service;

import br.com.alura.springdata.domain.Cargo;
import br.com.alura.springdata.domain.Unidade;
import br.com.alura.springdata.dto.CargoDTO;
import br.com.alura.springdata.dto.SaveCargoDTO;
import br.com.alura.springdata.dto.UnidadeDTO;
import br.com.alura.springdata.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CargoService {

    @Autowired
    private CargoRepository repository;

    public List<CargoDTO> findAllCargo(){
        return repository.findAllByStatusTrue().stream().map(CargoDTO::new).toList();
    }
    public CargoDTO findCargoById(UUID uuid){
        return new CargoDTO(repository.getReferenceById(uuid));
    }

    public CargoDTO saveCargo(SaveCargoDTO dados){
        Cargo cargo = repository.save(new Cargo(dados));
        return new CargoDTO(cargo);
    }

    public CargoDTO updateCargo(CargoDTO dados){
        repository.getReferenceById(dados.id());
        return new CargoDTO(repository.save(new Cargo(dados)));
    }

    public void deleteCargo(UUID id){
        Cargo cargo = repository.getReferenceById(id);
        cargo.setStatus(false);
    }
}
