package com.quad.ScanwordApp.dto;


import com.quad.ScanwordApp.model.DraftScanword;
import com.quad.ScanwordApp.model.enums.Role;
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
public class UserDto {

    private UUID id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Role role;

    private List<DraftScanword> draftScanwords = new ArrayList<>();
}
