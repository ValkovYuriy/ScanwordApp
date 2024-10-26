package com.quad.ScanwordApp.mapper;


import com.quad.ScanwordApp.dto.DraftScanwordDto;
import com.quad.ScanwordApp.model.DraftScanword;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DraftScanwordMapper {

    @Mapping(target = "scanwordId",source = "scanword.id")
    DraftScanwordDto toDto(DraftScanword draftScanword);

    DraftScanword toDraftScanword(DraftScanwordDto draftScanwordDto);
}
