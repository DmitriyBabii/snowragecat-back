package com.snowragecat.main.jpa.repositories;

import com.snowragecat.main.jpa.entities.Verdict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerdictRepository extends JpaRepository<Verdict, Long> {
}
