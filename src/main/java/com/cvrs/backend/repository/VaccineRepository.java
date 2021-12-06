package com.cvrs.backend.repository;

import com.cvrs.backend.entity.VaccineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<VaccineEntity, Long> {
}
