package org.project.booktrackerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.project.booktrackerservice.dto.BookDTO;
import org.project.booktrackerservice.entity.BookEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
    BookEntity toBookEntity(BookDTO bookDTO);
    BookDTO toBookDTO(BookEntity bookEntity);
}
