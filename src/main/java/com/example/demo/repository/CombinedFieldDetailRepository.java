package com.example.demo.repository;

import com.example.demo.entity.CombinedFieldDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombinedFieldDetailRepository extends JpaRepository<CombinedFieldDetail, Integer> {

    List<CombinedFieldDetail> findByCombinedFieldCombinedFieldId(Integer combinedFieldId);

    boolean existsBySingleFieldSingleFieldId(Integer singleFieldId);
}
