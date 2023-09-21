package com.fit.repositories;

import com.fit.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
   @Query(value = "select * from PESSOA WHERE ID = :id", nativeQuery = true)
   Optional<Aluno> findById(Integer id);
}
