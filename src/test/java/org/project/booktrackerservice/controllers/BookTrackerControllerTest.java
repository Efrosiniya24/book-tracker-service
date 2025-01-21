package org.project.booktrackerservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.project.booktrackerservice.Enum.StatusEnum;
import org.project.booktrackerservice.dto.BookDTO;
import org.project.booktrackerservice.service.BookTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookTrackerController.class)
class BookTrackerControllerTest {

    @MockitoBean
    private BookTrackerService bookTrackerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createBook() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(post("/books/book-tracker/create-book/{id}", 1L))
                .andExpect(status().isOk());

        verify(bookTrackerService, times(1)).createBook(1L);
    }

    @Test
    void deleteBook() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(delete("/books/book-tracker/delete-book/{id}", 1L))
                .andExpect(status().isOk());

        verify(bookTrackerService, times(1)).softDeleteBook(1L);
    }

    @Test
    void getFreeBooks() throws Exception {
        //given
        List<BookDTO> books = Arrays.asList(
                new BookDTO(1L, 1L, StatusEnum.FREE, LocalDateTime.now(), LocalDateTime.now(), false),
                new BookDTO(2L, 2L, StatusEnum.FREE, LocalDateTime.now(), LocalDateTime.now(), false)
        );
        String booksJson = objectMapper.writeValueAsString(books);
        //when
        when(bookTrackerService.getFreeBooks()).thenReturn(books);

        //then
        mockMvc.perform(get("/books/book-tracker/free-books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(booksJson))
                .andExpect(status().isOk());

        verify(bookTrackerService, times(1)).getFreeBooks();
    }

    @Test
    void updateBookStatus() throws Exception {
        //given
        BookDTO bookDTO = new BookDTO(1L, 1L, StatusEnum.FREE, LocalDateTime.now(), LocalDateTime.now(), false);
        String bookJson = objectMapper.writeValueAsString(bookDTO);

        //when
        when(bookTrackerService.updateStatus(1L, String.valueOf(StatusEnum.TAKEN))).thenReturn(bookDTO);

        //then
        mockMvc.perform(patch("/books/book-tracker/update-status/{id}", 1L)
                        .param("status", String.valueOf(StatusEnum.TAKEN))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson))
                .andExpect(status().isOk());

        verify(bookTrackerService, times(1)).updateStatus(1L, String.valueOf(StatusEnum.TAKEN));
    }
}