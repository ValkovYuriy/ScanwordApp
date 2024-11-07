package com.quad.ScanwordApp.service;


import com.quad.ScanwordApp.dto.ImageDto;
import com.quad.ScanwordApp.exception.NotFoundException;
import com.quad.ScanwordApp.mapper.ImageMapper;
import com.quad.ScanwordApp.model.Image;
import com.quad.ScanwordApp.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    private final ImageMapper imageMapper;

    public List<ImageDto> findAllImages() {
        return imageRepository.findAll()
                .stream()
                .map(imageMapper::toDto)
                .toList();
    }

    public ImageDto addImage(ImageDto imageDto) {
        Image image = imageMapper.toImage(imageDto);
        return imageMapper.toDto(imageRepository.save(image));
    }

    public ImageDto updateImage(ImageDto imageDto, UUID id) {
        Image image = imageRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format("Картинка с id %s не найдена",id)));
        image.setImage(imageDto.getImage());
        image.setQuestion(imageDto.getQuestion());
        image.setAnswer(imageDto.getAnswer());
        return imageMapper.toDto(imageRepository.save(image));
    }

    public ImageDto findImageById(UUID id) {
        return imageRepository.findById(id)
                .stream()
                .map(imageMapper::toDto)
                .findFirst()
                .orElse(null);
    }

    public void deleteImage(UUID id) {
        imageRepository.deleteById(id);
    }
}
