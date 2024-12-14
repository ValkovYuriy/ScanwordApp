package com.quad.ScanwordApp.model.json;


import com.quad.ScanwordApp.model.enums.TaskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    private String word;
}
