package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.dto.DictionaryDto;
import com.quad.ScanwordApp.service.DictionaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping
    public List<DictionaryDto> findAllWords(){
        List<DictionaryDto> all = dictionaryService.findAllWords();
        return all;
    }


    @PostMapping
    public DictionaryDto addWord(@RequestBody @Valid DictionaryDto dictionaryDto){
        DictionaryDto dictionaryDto1 = dictionaryService.addWord(dictionaryDto);
        return dictionaryDto1;
    }


    @DeleteMapping
    public void deleteWord(@RequestParam UUID id){
        dictionaryService.deleteWord(id);
    }
}
