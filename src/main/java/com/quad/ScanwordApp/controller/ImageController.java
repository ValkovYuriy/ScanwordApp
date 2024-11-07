package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.dto.ImageDto;
import com.quad.ScanwordApp.service.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public List<ImageDto> findAllImages(){
        List<ImageDto> allImages = imageService.findAllImages();
        return allImages;
    }

    @GetMapping("/{id}")
    public ImageDto getImage(@PathVariable UUID id) {
        return imageService.findImageById(id);
    }

    @GetMapping("/{id}/base64") // обновляем маршрутизацию
    public String getImageBase64(@PathVariable UUID id) {
        // Получаем изображение по ID
        ImageDto imageDto = imageService.findImageById(id);
        // Возвращаем Base64 строку
        return imageDto.getBase64Image();
    }

    @PostMapping
    public ImageDto addImage(@RequestBody @Valid ImageDto imageDto){
        return imageService.addImage(imageDto);
    }

    @PutMapping
    public ImageDto updateImage(@RequestBody @Valid ImageDto imageDto, @RequestParam UUID id){
        ImageDto imageDto1 = imageService.updateImage(imageDto,id);
        return imageDto1;
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable UUID id){
        imageService.deleteImage(id);
    }
}
