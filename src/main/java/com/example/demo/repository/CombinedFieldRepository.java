package com.example.demo.repository;

import com.example.demo.entity.CombinedField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombinedFieldRepository extends JpaRepository<CombinedField, Integer> {

    List<CombinedField> findByFacilityFacilityId(Integer facilityId);

    List<CombinedField> findByStatus(String status);

    List<CombinedField> findByFieldType(String fieldType);
}
