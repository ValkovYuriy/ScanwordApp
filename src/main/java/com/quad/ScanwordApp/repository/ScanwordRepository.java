package com.quad.ScanwordApp.repository;

import com.quad.ScanwordApp.model.Scanword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScanwordRepository extends JpaRepository<Scanword, UUID> {

    @Query("SELECT s FROM Scanword s " +
            "LEFT JOIN DraftScanword d on s.id = d.scanword.id AND d.owner.id = :userId " +
            "WHERE d.scanword.id IS NULL AND s.isCreated = true")
    List<Scanword> findAllNewScanwordsForUser(@Param("userId") UUID userId);
}
