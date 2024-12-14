package com.quad.ScanwordApp.model.json;


import com.quad.ScanwordApp.model.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.rmi.server.UID;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cell {

    private int row;

    private int col;

    private Character letter;

    private boolean task;

    private TaskType taskType;

    private UUID taskId;

    private String word;
}
