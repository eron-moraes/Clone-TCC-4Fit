package com.fit.services;

import com.fit.domain.Aluno;
import com.fit.domain.Instrutor;
import com.fit.domain.Matricula;
import com.fit.domain.dtos.MatriculaDTO;
import com.fit.domain.enums.Status;
import com.fit.domain.enums.Turno;
import com.fit.repositories.MatriculaRepository;
import com.fit.services.exceptions.ObjectnotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private AlunoService alunoService;
    @Autowired
    private InstrutorService instrutorService;
    public Matricula findById(Integer id) {
        Optional<Matricula> obj = matriculaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
    }

    public Matricula create( @Valid MatriculaDTO objDTO ) {
        return matriculaRepository.save(newMatricula(objDTO));
    }

    private Matricula newMatricula(MatriculaDTO obj){
        Instrutor instrutor = instrutorService.findById(obj.getInstrutor());
        Aluno aluno = alunoService.findById(obj.getAluno());

        Matricula matricula = new Matricula();
        if (obj.getId() != null){
            matricula.setId(obj.getId());
        }
        if(obj.getStatus().equals(2)){
            matricula.setDataFechamento(LocalDate.now());
        }
        matricula.setInstrutor(instrutor);
        matricula.setAluno(aluno);
        matricula.setStatus(Status.toEnum(obj.getStatus()));
        matricula.setTurno(Turno.toEnum(obj.getTurno()));
        matricula.setObservacoes(obj.getObservacoes());
        matricula.setTitulo(obj.getTitulo());
        return matricula;

    }
    public void delete(Integer id) {
        matriculaRepository.deleteById(id);
    }

    public List<Matricula> findAll() {
        return matriculaRepository.findAll();
    }

    public Matricula update(Integer id,@Valid MatriculaDTO objDTO) {
        objDTO.setId(id);
        Matricula oldObj = findById(id);
        oldObj = newMatricula(objDTO);
        return matriculaRepository.save(oldObj);
    }
}
