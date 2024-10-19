package com.quad.ScanwordApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
