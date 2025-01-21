package org.project.booktrackerservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.project.booktrackerservice.service.BookTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        mockMvc.perform(post("/books/book-tracker//create-book/{id}", 1L))
                .andExpect(status().isOk());

        verify(bookTrackerService, times(1)).createBook(1L);
    }
}