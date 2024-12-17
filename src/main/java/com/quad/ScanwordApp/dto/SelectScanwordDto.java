package com.quad.ScanwordApp.dto;


import com.quad.ScanwordApp.model.json.Cell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectScanwordDto {

    private UUID scanwordId;

    private UUID draftId;

    private String name;

    private Integer numberOfHints;

    private byte[] preview;

    private List<Cell> content;

    public String getBase64Image()
    {
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(preview);
    }
}
