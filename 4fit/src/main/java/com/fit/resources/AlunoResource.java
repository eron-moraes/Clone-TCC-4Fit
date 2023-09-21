package com.fit.resources;

import com.fit.domain.Aluno;
import com.fit.domain.dtos.AlunoDTO;
import com.fit.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoResource {
    @Autowired
    private AlunoService alunoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> findById(@PathVariable Integer id) {
        Aluno obj = alunoService.findById(id);
        return ResponseEntity.ok().body(new AlunoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> findAll() {
        List<Aluno> list = alunoService.findAll();
        List<AlunoDTO> listDTO = list.stream().map(obj -> new AlunoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> create(@Valid @RequestBody AlunoDTO objDTO) {
        Aluno newObj = alunoService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<AlunoDTO> upadate(@Valid @RequestBody AlunoDTO objDTO) {
        Aluno obj = alunoService.update(objDTO);
        return ResponseEntity.ok().body(new AlunoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            alunoService.delete(id);
            return ResponseEntity.ok("Aluno deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar aluno: " + e.getMessage());
        }
    }
}
