package com.snowragecat.main.jpa.repositories;

import com.snowragecat.main.jpa.models.entities.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
}
