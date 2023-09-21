package com.fit.repositories;

import com.fit.domain.Aluno;
import com.fit.domain.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InstrutorRepository extends JpaRepository<Instrutor, Integer> {
    @Query(value = "select * from PESSOA WHERE ID = :id", nativeQuery = true)
    Optional<Instrutor> findById(Integer id);
}
