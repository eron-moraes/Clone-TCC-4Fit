package com.fit.services;

import com.fit.domain.Aluno;
import com.fit.domain.Matricula;
import com.fit.domain.Pessoa;
import com.fit.domain.dtos.AlunoDTO;
import com.fit.repositories.AlunoRepository;
import com.fit.repositories.MatriculaRepository;
import com.fit.repositories.PessoaRepository;
import com.fit.services.exceptions.DataIntegrityViolationException;
import com.fit.services.exceptions.ObjectnotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private MatriculaRepository matriculaRepository;

    public Aluno findById(Integer id) {
        Optional<Aluno> obj = alunoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public Aluno create(AlunoDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Aluno newObj = new Aluno(objDTO);
        return alunoRepository.save(newObj);
    }

    public Aluno update( @Valid AlunoDTO objDTO) {
        Aluno newObj = new Aluno(objDTO);
        return alunoRepository.save(newObj);

    }

    @Transactional
    public ResponseEntity<String> delete(Integer id) {
        try {
            Matricula matricula = matriculaRepository.findByAlunoId(id);

            if (matricula != null) {
                matriculaRepository.deleteByAluno_Id(id);
            }

            alunoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Aluno e matrícula excluídos com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir aluno e matrícula: " + e.getMessage());

        }
    }

    private void validaPorCpfEEmail(AlunoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }

}
