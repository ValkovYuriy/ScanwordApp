package com.quad.ScanwordApp.dto;

import com.quad.ScanwordApp.model.json.Cell;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Base64;
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
    private UUID dictionaryId;

    private List<Cell> content = new ArrayList<>();

    @NotNull
    private Boolean isCreated;

    @NotNull
    private Integer numberOfHints;

    public String getBase64Image()
    {
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(preview);
    }
}
