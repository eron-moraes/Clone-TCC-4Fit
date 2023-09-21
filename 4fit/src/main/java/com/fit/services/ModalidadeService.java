package com.fit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fit.domain.Modalidade;
import com.fit.domain.dtos.ModalidadeDTO;
import com.fit.repositories.ModalidadeRepository;
import com.fit.services.exceptions.DataIntegrityViolationException;
import com.fit.services.exceptions.ObjectnotFoundException;

import jakarta.validation.Valid;

@Service
public class ModalidadeService {

	@Autowired
	private ModalidadeRepository modalidadeRepository;

	public Modalidade findById(Integer id) {
		Optional<Modalidade> obj = modalidadeRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
	}

	public List<Modalidade> findAll() {
		return modalidadeRepository.findAll();
	}
	
	public Modalidade create(ModalidadeDTO objDTO) {
        Modalidade newObj = new Modalidade(objDTO);
        return modalidadeRepository.save(newObj);
    }
    public Modalidade update(Integer id, @Valid ModalidadeDTO objDTO) {
        objDTO.setId(id);
        Modalidade oldObj = findById(id);
        oldObj = new Modalidade(objDTO);
        return modalidadeRepository.save(oldObj);
    }
    public void delete(Integer id) {
        Modalidade obj = findById(id);
        if(obj.getModalidade().size() > 0){
            throw new DataIntegrityViolationException("A modalidade contém alunos matriculados em suas aulas e não pode ser deletado!");
        }
            modalidadeRepository.deleteById(id);
    }

}
