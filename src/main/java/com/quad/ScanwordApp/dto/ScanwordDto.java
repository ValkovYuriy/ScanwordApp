package com.quad.ScanwordApp.dto;

import com.quad.ScanwordApp.model.Cell;
import com.quad.ScanwordApp.model.Dictionary;
import com.quad.ScanwordApp.model.Image;
import com.quad.ScanwordApp.model.Melody;
import com.quad.ScanwordApp.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScanwordDto {

    private UUID id;

    @NotBlank
    private String name;

    @PositiveOrZero
    private int width;

    @PositiveOrZero
    private int height;

    private byte[] preview;


    private List<Cell> content = new ArrayList<>();


    private List<User> users = new ArrayList<>();


    private List<Dictionary> answers = new ArrayList<>();


    private List<Melody> melodies = new ArrayList<>();


    private List<Image> images = new ArrayList<>();

    private boolean isCreated;
}
