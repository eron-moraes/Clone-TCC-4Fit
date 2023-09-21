package com.fit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.fit.domain.Fatura;

public interface FaturaRepository extends JpaRepository<Fatura, Integer> {

}
