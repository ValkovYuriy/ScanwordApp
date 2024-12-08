package com.quad.ScanwordApp.repository;

import com.quad.ScanwordApp.model.Melody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface MelodyRepository extends JpaRepository<Melody, UUID> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Melody m WHERE m.name = :name")
    void deleteByName(@Param("name") String name);
}
