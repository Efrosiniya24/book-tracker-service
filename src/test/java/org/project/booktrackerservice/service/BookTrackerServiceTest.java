package org.project.booktrackerservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.booktrackerservice.Enum.StatusEnum;
import org.project.booktrackerservice.dto.BookDTO;
import org.project.booktrackerservice.entity.BookEntity;
import org.project.booktrackerservice.mapper.BookMapper;
import org.project.booktrackerservice.repository.BookRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookTrackerServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookTrackerService bookTrackerService;

    @Test
    void createBookTest() {
        // given
        Long bookId = 1L;
        BookEntity bookEntity = new BookEntity(bookId, bookId, StatusEnum.FREE, LocalDateTime.now(), LocalDateTime.now(), false);

        BookDTO bookDTO = new BookDTO(bookId, bookId, StatusEnum.FREE, LocalDateTime.now(), LocalDateTime.now(), false);

        // when
        when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntity);
        when(bookMapper.toBookDTO(bookEntity)).thenReturn(bookDTO);

        // then
        BookDTO actualBookDTO = bookTrackerService.createBook(bookId);
        assertEquals(bookDTO, actualBookDTO);

        verify(bookMapper, times(1)).toBookDTO(bookEntity);
    }

    @Test
    void softDeleteBookTest() {
        //given
        Long bookId = 1L;
        BookEntity bookEntity = new BookEntity(bookId, bookId, StatusEnum.FREE, LocalDateTime.now(), LocalDateTime.now(), false);

        //when
        when(bookRepository.findByBookId(bookId)).thenReturn(Optional.of(bookEntity));
        bookTrackerService.softDeleteBook(bookId);

        //then
    }
}