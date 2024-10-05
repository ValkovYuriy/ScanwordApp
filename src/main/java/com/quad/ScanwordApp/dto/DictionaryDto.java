package com.quad.ScanwordApp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryDto {

    private UUID id;

    private String word;

    private String question;
}
