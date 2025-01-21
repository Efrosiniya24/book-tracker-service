package org.project.booktrackerservice.mapper;

import org.mapstruct.Mapper;
import org.project.booktrackerservice.dto.BookDTO;
import org.project.booktrackerservice.entity.BookEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    List<BookDTO> toBookDTOList(List<BookEntity> bookEntities);

}
