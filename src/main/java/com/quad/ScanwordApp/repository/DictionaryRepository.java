package com.quad.ScanwordApp.repository;

import com.quad.ScanwordApp.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.quad.ScanwordApp.model.Dictionary;
import java.util.UUID;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, UUID> {
}
