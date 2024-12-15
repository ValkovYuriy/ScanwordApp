package com.quad.ScanwordApp.repository;

import com.quad.ScanwordApp.model.DraftScanword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DraftScanwordRepository extends JpaRepository<DraftScanword, UUID> {

    @Query("SELECT d FROM DraftScanword d WHERE d.owner.id = :ownerId")
    List<DraftScanword> findAllByOwnerId(UUID ownerId);
}
