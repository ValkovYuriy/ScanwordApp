package com.quad.ScanwordApp.mapper;


import com.quad.ScanwordApp.dto.MelodyDto;
import com.quad.ScanwordApp.model.Melody;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MelodyMapper {

    Melody toMelody(MelodyDto melodyDto);

    MelodyDto toDto(Melody melody);
}
