package com.fit.resources;

import com.fit.domain.Aluno;
import com.fit.domain.Instrutor;
import com.fit.domain.dtos.AlunoDTO;
import com.fit.domain.dtos.InstrutorDTO;
import com.fit.services.InstrutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/instrutor")
public class InstrutorResource {
    @Autowired
    private InstrutorService instrutorService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<InstrutorDTO> findById(@PathVariable Integer id){
        Instrutor obj = instrutorService.findById(id);
        return ResponseEntity.ok().body(new InstrutorDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<InstrutorDTO>> findAll(){
        List<Instrutor> list = instrutorService.findAll();
        List<InstrutorDTO> listDTO = list.stream().map(obj -> new InstrutorDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<InstrutorDTO> create(@Valid @RequestBody InstrutorDTO objDTO){
        Instrutor newObj = instrutorService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<InstrutorDTO> upadate(@Valid @RequestBody InstrutorDTO objDTO) {
        Instrutor obj = instrutorService.update(objDTO);
        return ResponseEntity.ok().body(new InstrutorDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            instrutorService.delete(id);
            return ResponseEntity.ok("Instrutor deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao deletar instrutor: " + e.getMessage());
        }
    }
}
