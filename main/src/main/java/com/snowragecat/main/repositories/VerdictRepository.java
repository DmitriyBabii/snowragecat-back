package com.snowragecat.main.repositories;

import com.snowragecat.main.models.entities.Verdict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerdictRepository extends JpaRepository<Verdict, Long> {
}
