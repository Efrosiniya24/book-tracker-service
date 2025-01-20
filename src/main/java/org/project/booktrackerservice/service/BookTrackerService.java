package org.project.booktrackerservice.service;

import lombok.AllArgsConstructor;
import org.project.booktrackerservice.Enum.StatusEnum;
import org.project.booktrackerservice.entity.BookEntity;
import org.project.booktrackerservice.mapper.BookMapper;
import org.project.booktrackerservice.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookTrackerService {

    private final BookRepository bookRepository;

    public void createBook(Long id) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId(id);
        bookEntity.setStatus(StatusEnum.FREE);

        bookRepository.save(bookEntity);
    }
}
