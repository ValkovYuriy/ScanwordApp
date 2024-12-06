package com.quad.ScanwordApp.controller;


import com.quad.ScanwordApp.dto.MelodyDto;
import com.quad.ScanwordApp.service.MelodyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/melody")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class MelodyController {

    private final MelodyService melodyService;

    @GetMapping
    public List<MelodyDto> findAllMelodies() {
        List<MelodyDto> allMelodies = melodyService.findAllMelodies();
        return allMelodies;
    }

    @GetMapping("/{id}")
    public MelodyDto findMelodyById(@PathVariable UUID id) throws IOException {
        MelodyDto melodyDto = melodyService.findMelodyById(id);
        return melodyDto;
    }

    @PostMapping
    public MelodyDto addMelody(@RequestBody @Valid MelodyDto melodyDto) {
        MelodyDto melodyDto1 = melodyService.addMelody(melodyDto);
        return melodyDto1;
    }

    @PutMapping
    public MelodyDto updateMelody(@RequestBody @Valid MelodyDto melodyDto, @RequestParam UUID id) {
        MelodyDto melodyDto1 = melodyService.updateMelody(melodyDto, id);
        return melodyDto1;
    }

    @DeleteMapping("/{id}")
    public void deleteMelody(@PathVariable UUID id) {
        melodyService.deleteMelody(id);
    }
}
