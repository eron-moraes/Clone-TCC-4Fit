package com.fit.services;

import com.fit.domain.Aluno;
import com.fit.domain.Instrutor;
import com.fit.domain.Matricula;
import com.fit.domain.Pessoa;
import com.fit.domain.dtos.AlunoDTO;
import com.fit.domain.dtos.InstrutorDTO;
import com.fit.repositories.InstrutorRepository;
import com.fit.repositories.MatriculaRepository;
import com.fit.repositories.PessoaRepository;
import com.fit.services.exceptions.DataIntegrityViolationException;
import com.fit.services.exceptions.ObjectnotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrutorService {
    @Autowired
    private InstrutorRepository instrutorRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private MatriculaRepository matriculaRepository;

    public Instrutor findById(Integer id){
        Optional<Instrutor> obj = instrutorRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Instrutor> findAll() {
        return instrutorRepository.findAll();
    }

    public Instrutor create(InstrutorDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Instrutor newObj = new Instrutor(objDTO);
        return instrutorRepository.save(newObj);
    }
    public Instrutor update(@Valid InstrutorDTO objDTO) {
        Instrutor newObj = new Instrutor(objDTO);
        return instrutorRepository.save(newObj);

    }
    @Transactional
    public ResponseEntity<String> delete(Integer id) {
        try {
            Matricula matricula = matriculaRepository.findByInstrutorId(id);

            if (matricula != null) {
                matriculaRepository.deleteByInstrutor_Id(id);
            }

            instrutorRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Aluno e matrícula excluídos com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir aluno e matrícula: " + e.getMessage());

        }
    }
    private void validaPorCpfEEmail(InstrutorDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }



}
