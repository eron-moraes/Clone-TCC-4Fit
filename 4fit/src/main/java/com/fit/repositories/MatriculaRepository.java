package com.fit.repositories;

import com.fit.domain.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    @Modifying
    @Query(value = "DELETE FROM Matricula m WHERE m.aluno.id = ?1")
    void deleteByAluno_Id(Integer id);

    Matricula findByAlunoId(Integer id);


    @Modifying
    @Query(value = "DELETE FROM Matricula m WHERE m.instrutor.id = ?1")
    void deleteByInstrutor_Id(Integer id);

    Matricula findByInstrutorId(Integer id);
}


