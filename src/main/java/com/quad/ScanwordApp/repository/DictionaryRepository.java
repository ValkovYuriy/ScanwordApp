package com.quad.ScanwordApp.repository;

import com.quad.ScanwordApp.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, UUID> {

    @Query("SELECT d FROM Dictionary d WHERE d.name = :name")
    Dictionary findDictionaryByName(@Param("name") String name);
}
