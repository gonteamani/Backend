package com.rs.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rs.Entity.Resignation;

import jakarta.transaction.Transactional;

public interface ResignationRepository extends JpaRepository<Resignation,Long>{
 Optional<Resignation> findByEmployeeId(Long id);
 
 @Modifying
 @Transactional
 @Query("DELETE FROM Resignation r WHERE r.id = :id")
 void deleteByIdManually(@Param("id") Long id);

}