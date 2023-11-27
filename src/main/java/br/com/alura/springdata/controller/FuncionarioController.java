package br.com.alura.springdata.controller;

import br.com.alura.springdata.domain.Funcionario;
import br.com.alura.springdata.dto.FuncionarioDTO;
import br.com.alura.springdata.dto.SaveFuncionarioDTO;
import br.com.alura.springdata.service.FuncionarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAllFuncionario(){
        return ResponseEntity.ok(service.findAllFuncionario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> findFuncionarioById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findFuncionarioById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<FuncionarioDTO>> findAllByNome(@RequestParam(value = "nome",
                                                                            defaultValue = "",
                                                                            required=false) String nome){
        return ResponseEntity.ok(service.findAllByNome(nome));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<FuncionarioDTO> saveFuncionario(@RequestBody @Valid SaveFuncionarioDTO dados, UriComponentsBuilder uriBuilder){
        FuncionarioDTO funcionario = service.saveFuncionario(dados);
        var uri = uriBuilder.path("/funcionarios/{id}")
                .buildAndExpand(funcionario.id())
                .toUri();

        return ResponseEntity.created(uri).body(funcionario);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<FuncionarioDTO> updateFuncionario(@RequestBody @Valid FuncionarioDTO dados){
        return ResponseEntity.ok(service.updateFuncionario(dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteFuncionario(@PathVariable UUID id){
        service.deleteFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
