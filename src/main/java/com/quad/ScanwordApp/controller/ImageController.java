package com.quad.ScanwordApp.controller;

import com.quad.ScanwordApp.dto.ImageDto;
import com.quad.ScanwordApp.dto.ResponseDto;
import com.quad.ScanwordApp.service.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDto<ImageDto>> addImage(@RequestParam("image") MultipartFile image,
                                                        @RequestParam("question") String question,
                                                        @RequestParam("answer") String answer) throws IOException {
        // Создаем новый ImageDto
        ImageDto imageDto = new ImageDto();
        imageDto.setImage(image.getBytes()); // Получаем массив байтов из файла
        imageDto.setQuestion(question);
        imageDto.setAnswer(answer);
        ImageDto result = imageService.addImage(imageDto);
        return ResponseEntity.ok(new ResponseDto<>("",result));
    }

    @PutMapping
    public ImageDto updateImage(@RequestBody @Valid ImageDto imageDto, @RequestParam UUID id){
        ImageDto imageDto1 = imageService.updateImage(imageDto,id);
        return imageDto1;
    }

//    @DeleteMapping("/{id}")
//    public void deleteImage(@PathVariable UUID id){
//        imageService.deleteImage(id);
//    }

    @DeleteMapping("/{answer}")
    public void deleteImageByAnswer(@PathVariable String answer){
        imageService.deleteImageByAnswer(answer);
    }
}
