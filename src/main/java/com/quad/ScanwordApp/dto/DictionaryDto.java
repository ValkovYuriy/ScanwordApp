package com.quad.ScanwordApp.dto;


import com.quad.ScanwordApp.model.json.DictionaryData;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryDto {

    private UUID id;

    @NotBlank
    private String name;

    List<DictionaryData> dictionaryData = new ArrayList<>();
}
