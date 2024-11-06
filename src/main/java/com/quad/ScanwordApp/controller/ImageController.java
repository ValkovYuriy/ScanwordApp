package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.dto.ImageDto;
import com.quad.ScanwordApp.service.ImageService;
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

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public List<ImageDto> findAllImages(){
        List<ImageDto> allMelodies = imageService.findAllImages();
        return allMelodies;
    }


    @PostMapping
    public ImageDto addImage(@RequestBody @Valid ImageDto imageDto){
        ImageDto imageDto1 = imageService.addImage(imageDto);
        return imageDto1;
    }

    @PutMapping
    public ImageDto updateImage(@RequestBody @Valid ImageDto imageDto, @RequestParam UUID id){
        ImageDto imageDto1 = imageService.updateImage(imageDto,id);
        return imageDto1;
    }

    @DeleteMapping
    public void deleteImage(@RequestParam UUID id){
        imageService.deleteImage(id);
    }
}
