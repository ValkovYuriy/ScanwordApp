package com.quad.ScanwordApp.controller;


import com.quad.ScanwordApp.dto.MelodyDto;
import com.quad.ScanwordApp.service.MelodyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public MelodyDto findMelodyById(@PathVariable UUID id)  {
        MelodyDto melodyDto = melodyService.findMelodyById(id);
        return melodyDto;
    }

    @PostMapping
    public MelodyDto addMelody(@RequestParam("audio") MultipartFile melody,
                                  @RequestParam("question") String question,
                                  @RequestParam("answer") String answer,
                                  @RequestParam("name") String name) throws IOException {

        MelodyDto melodyDto = new MelodyDto();
        melodyDto.setQuestion(question);
        melodyDto.setAnswer(answer);
        melodyDto.setMelody(melody.getBytes());
        melodyDto.setName(name);
        return melodyService.addMelody(melodyDto);
    }

    @PutMapping
    public MelodyDto updateMelody(@RequestBody @Valid MelodyDto melodyDto, @RequestParam UUID id) {
        MelodyDto melodyDto1 = melodyService.updateMelody(melodyDto, id);
        return melodyDto1;
    }

//    @DeleteMapping("/{id}")
//    public void deleteMelody(@PathVariable UUID id) {
//        melodyService.deleteMelody(id);
//    }

    @DeleteMapping("/{name}")
    public void deleteMelodyByName(@PathVariable String name)  {
        melodyService.deleteMelodyByName(name);
    }
}
