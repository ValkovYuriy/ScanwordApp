package com.quad.ScanwordApp.dto;


import com.quad.ScanwordApp.model.Cell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DraftScanwordDto {

    private UUID id;

    private UUID scanwordId;

    private List<Cell> content;
}
