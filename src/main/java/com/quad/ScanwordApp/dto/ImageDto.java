package com.quad.ScanwordApp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    private UUID id;

    private byte[] image;

    @NotBlank
    private String question;

    @NotBlank
    private String answer;

    public String getBase64Image()
    {
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(image);
    }
}
