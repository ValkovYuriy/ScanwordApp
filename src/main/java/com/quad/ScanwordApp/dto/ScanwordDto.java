package com.quad.ScanwordApp.dto;

import com.quad.ScanwordApp.model.json.Cell;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private UUID creatorId;

    private List<Cell> content = new ArrayList<>();

    private boolean isCreated;
}
