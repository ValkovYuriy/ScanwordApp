package com.quad.ScanwordApp.mapper;

import com.quad.ScanwordApp.dto.ImageDto;
import com.quad.ScanwordApp.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageDto toDto(Image image);

    Image toImage(ImageDto imageDto);
}
