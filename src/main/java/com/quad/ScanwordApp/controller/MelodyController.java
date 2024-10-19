package com.quad.ScanwordApp.controller;


import com.quad.ScanwordApp.service.MelodyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.quad.ScanwordApp.dto.MelodyDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/melody")
@RequiredArgsConstructor
public class MelodyController {
    
    private final MelodyService melodyService;

    @GetMapping
    public List<MelodyDto> findAllMelodies(){
        List<MelodyDto> allMelodies = melodyService.findAllMelodies();
        return allMelodies;
    }


    @PostMapping
    public MelodyDto addMelody(@RequestBody @Valid MelodyDto melodyDto){
        MelodyDto melodyDto1 = melodyService.addMelody(melodyDto);
        return melodyDto1;
    }

    @PutMapping
    public MelodyDto updateMelody(@RequestBody @Valid MelodyDto melodyDto, @RequestParam UUID id){
        MelodyDto melodyDto1 = melodyService.updateMelody(melodyDto,id);
        return melodyDto1;
    }

    @DeleteMapping
    public void deleteMelody(@RequestParam UUID id){
        melodyService.deleteMelody(id);
    }
}
