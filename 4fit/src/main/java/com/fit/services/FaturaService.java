package com.fit.services;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fit.domain.Fatura;
import com.fit.domain.Matricula;
import com.fit.domain.Modalidade;
import com.fit.domain.enums.Status;
import com.fit.repositories.FaturaRepository;
import com.fit.repositories.MatriculaRepository;
import com.fit.repositories.ModalidadeRepository;

import jakarta.transaction.Transactional;

@Service
public class FaturaService {

    @Autowired
    private FaturaRepository faturaRepository;
    
    @Autowired
    private MatriculaRepository matriculaRepository;
    
    @Autowired
    private ModalidadeRepository modalidadeRepository;
    
    @Transactional
    public void criarFatura(Integer matriculaId, Integer modalidadeId) {
        // Recupere a Matricula e a Modalidade com base nos IDs passados como argumentos
        Matricula matricula = matriculaRepository.findById(matriculaId).orElse(null);
        Modalidade modalidade = modalidadeRepository.findById(modalidadeId).orElse(null);

        if (matricula != null && modalidade != null) {
            // Crie e configure a Fatura
            Fatura fatura = new Fatura();
            fatura.setStatus(Status.PENDENTE);
            fatura.setMesReferencia(YearMonth.now());
            fatura.setDataPagamento(null);
            fatura.setMatricula(matricula);
            fatura.setModalidade(modalidade);

            // Salve a Fatura no banco de dados
            faturaRepository.save(fatura);
        } else {
  
        }
    }
}

