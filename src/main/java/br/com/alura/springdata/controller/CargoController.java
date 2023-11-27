package br.com.alura.springdata.controller;

import br.com.alura.springdata.domain.Cargo;
import br.com.alura.springdata.dto.CargoDTO;
import br.com.alura.springdata.dto.SaveCargoDTO;
import br.com.alura.springdata.service.CargoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService service;

    @GetMapping
    public ResponseEntity<List<CargoDTO>> findAllCargo(){
        return ResponseEntity.ok(service.findAllCargo());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> findCargoById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findCargoById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CargoDTO> saveCargo(@RequestBody @Valid SaveCargoDTO dados, UriComponentsBuilder uriBuilder){
        CargoDTO cargo = service.saveCargo(dados);
        var uri = uriBuilder.path("/cargos/{id}")
                .buildAndExpand(cargo.id())
                .toUri();

        return ResponseEntity.created(uri).body(cargo);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CargoDTO> updateCargo(@RequestBody @Valid CargoDTO dados){
        return ResponseEntity.ok(service.updateCargo(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCargo(@PathVariable UUID id){
        service.deleteCargo(id);
        return ResponseEntity.noContent().build();
    }
}
