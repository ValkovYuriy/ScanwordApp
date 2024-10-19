package com.quad.ScanwordApp.mapper;

import com.quad.ScanwordApp.dto.ScanwordDto;
import com.quad.ScanwordApp.model.Scanword;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScanwordMapper {

    ScanwordDto toDto(Scanword scanword);

    Scanword toScanword(ScanwordDto scanwordDto);
}
