package org.project.booktrackerservice.service;

import jakarta.persistence.EntityNotFoundException;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void deleteBookNotFoundTest() {
        //given
        Long bookId = 1L;

        //when
        when(bookRepository.findByBookId(bookId)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class, () -> bookTrackerService.softDeleteBook(bookId));
    }

    @Test
    void getFreeBooksTest() {
        //given
        List<BookEntity> bookEntityList = Arrays.asList(
                new BookEntity(1L, 1L, StatusEnum.FREE, LocalDateTime.now(), LocalDateTime.now(), false),
                new BookEntity(2L, 2L, StatusEnum.FREE, LocalDateTime.now(), LocalDateTime.now(), false)
        );

        List<BookDTO> bookDTOList = Arrays.asList(
                new BookDTO(1L, 1L, StatusEnum.FREE, LocalDateTime.now(), LocalDateTime.now(), false),
                new BookDTO(2L, 2L, StatusEnum.FREE, LocalDateTime.now(), LocalDateTime.now(), false)
        );

        //when
        when(bookRepository.findAllByStatus(StatusEnum.FREE)).thenReturn(bookEntityList);
        when(bookMapper.toBookDTOList(bookEntityList)).thenReturn(bookDTOList);

        //then
        assertEquals(bookDTOList, bookTrackerService.getFreeBooks());
    }

    @Test
    void updateStatusTest() {
        //given
        Long bookId = 1L;
        BookEntity bookEntity = new BookEntity(bookId, bookId, StatusEnum.FREE, LocalDateTime.now(), LocalDateTime.now(), false);

        BookDTO expectedBookDTO = new BookDTO(bookId, bookId, StatusEnum.TAKEN, LocalDateTime.now(), LocalDateTime.now(), false);

        // when
        when(bookRepository.findByBookId(bookId)).thenReturn(Optional.of(bookEntity));
        when(bookRepository.save(any(BookEntity.class))).thenReturn(bookEntity);
        when(bookMapper.toBookDTO(bookEntity)).thenReturn(expectedBookDTO);

        // then
        BookDTO actualBookDTO = bookTrackerService.updateStatus(bookId, String.valueOf(StatusEnum.TAKEN));

        assertEquals(expectedBookDTO.getBookId(), actualBookDTO.getBookId());
        assertEquals(StatusEnum.TAKEN, actualBookDTO.getStatus());
        verify(bookRepository, times(1)).save(bookEntity);
    }

    @Test
    void updateStatusTestException() {
        //given
        Long bookId = 1L;

        // when
        when(bookRepository.findByBookId(bookId)).thenThrow(EntityNotFoundException.class);

        // then
        assertThrows(EntityNotFoundException.class, () -> bookTrackerService.updateStatus(bookId, String.valueOf(StatusEnum.TAKEN)));

    }
}