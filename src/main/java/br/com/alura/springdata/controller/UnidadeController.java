package br.com.alura.springdata.controller;

import br.com.alura.springdata.dto.UnidadeDTO;
import br.com.alura.springdata.dto.SaveUnidadeDTO;
import br.com.alura.springdata.service.UnidadeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    @Autowired
    private UnidadeService service;

    @GetMapping
    public ResponseEntity<List<UnidadeDTO>> findAllUnidade(){
        return ResponseEntity.ok(service.findAllUnidade());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDTO> findUnidadeById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findUnidadeById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UnidadeDTO> saveUnidade(@RequestBody @Valid SaveUnidadeDTO dados, UriComponentsBuilder uriBuilder){
        UnidadeDTO unidade = service.saveUnidade(dados);
        var uri = uriBuilder.path("/unidades/{id}")
                .buildAndExpand(unidade.id())
                .toUri();

        return ResponseEntity.created(uri).body(unidade);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<UnidadeDTO> updateUnidade(@RequestBody @Valid UnidadeDTO dados){
        return ResponseEntity.ok(service.updateUnidade(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUnidade(@PathVariable UUID id){
        service.deleteUnidade(id);
        return ResponseEntity.noContent().build();
    }
}
