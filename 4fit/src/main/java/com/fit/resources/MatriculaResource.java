package com.fit.resources;

import com.fit.domain.Matricula;
import com.fit.domain.dtos.MatriculaDTO;
import com.fit.services.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/matriculas")
public class MatriculaResource {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MatriculaDTO> findById(@PathVariable Integer id){
        Matricula obj = matriculaService.findById(id);
        return ResponseEntity.ok().body(new MatriculaDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> findAll(){
        List<Matricula> list = matriculaService.findAll();
        List<MatriculaDTO> listDTO = list.stream().map(obj -> new MatriculaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<MatriculaDTO> create(@Valid @RequestBody MatriculaDTO obj){
       Matricula newObj = matriculaService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<MatriculaDTO> update(@PathVariable Integer id, @Valid @RequestBody MatriculaDTO objDTO){
        Matricula newObj = matriculaService.update(id, objDTO);
        return ResponseEntity.ok().body(new MatriculaDTO(newObj));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        matriculaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
