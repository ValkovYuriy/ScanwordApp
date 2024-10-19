package com.quad.ScanwordApp.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryDto {

    private UUID id;

    @NotBlank
    private String word;

    @NotBlank
    private String question;
}
