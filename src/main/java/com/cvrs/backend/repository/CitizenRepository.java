package com.cvrs.backend.repository;

import com.cvrs.backend.entity.CitizenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenRepository extends JpaRepository<CitizenEntity, Long> {
    CitizenEntity findByCitizenshipEquals(String citizenship);

    List<CitizenEntity> findAllByVaccinatedStatus(String statusCode);

    List<CitizenEntity> findAllByAgeCategoryEntityId(Long id);
}
