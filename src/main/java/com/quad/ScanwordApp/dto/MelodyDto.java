package com.quad.ScanwordApp.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MelodyDto {

    private UUID id;

    private byte[] melody;

    @NotBlank
    private String question;

    @NotBlank
    private String answer;
}
