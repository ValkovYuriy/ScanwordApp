package com.quad.ScanwordApp.repository;

import com.quad.ScanwordApp.model.Scanword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScanwordRepository extends JpaRepository<Scanword, UUID> {
}
