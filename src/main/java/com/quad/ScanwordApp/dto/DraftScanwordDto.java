package com.quad.ScanwordApp.dto;


import com.quad.ScanwordApp.model.json.Cell;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DraftScanwordDto {

    private UUID id;

    @NotNull
    private UUID scanwordId;

    private UUID ownerId;

    private List<Cell> content = new ArrayList<>();

    private Boolean solved = false;

    private Integer numberOfHints;

    private byte[] preview;


}
