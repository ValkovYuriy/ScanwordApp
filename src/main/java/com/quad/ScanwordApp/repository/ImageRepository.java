package com.quad.ScanwordApp.repository;

import com.quad.ScanwordApp.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {

    @Modifying
    @Transactional // Добавьте аннотацию для управления транзакцией
    @Query("DELETE FROM Image i WHERE i.answer = :answer")
    void deleteImageByAnswer(String answer);
}
