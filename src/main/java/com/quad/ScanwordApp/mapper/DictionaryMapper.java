package com.quad.ScanwordApp.mapper;

import com.quad.ScanwordApp.dto.DictionaryDto;
import com.quad.ScanwordApp.model.Dictionary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DictionaryMapper {

    DictionaryDto  toDto(Dictionary dictionary);

    Dictionary toDictionary(DictionaryDto dictionaryDto);
}
