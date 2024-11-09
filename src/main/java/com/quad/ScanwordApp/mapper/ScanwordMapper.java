package com.quad.ScanwordApp.mapper;

import com.quad.ScanwordApp.dto.ScanwordDto;
import com.quad.ScanwordApp.model.Scanword;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScanwordMapper {

    @Mapping(target = "dictionaryId",source = "dictionary.id")
    ScanwordDto toDto(Scanword scanword);

    Scanword toScanword(ScanwordDto scanwordDto);
}
