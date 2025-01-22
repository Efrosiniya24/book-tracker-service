package org.project.booktrackerservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.project.booktrackerservice.Enum.StatusEnum;
import org.project.booktrackerservice.dto.BookDTO;
import org.project.booktrackerservice.entity.BookEntity;
import org.project.booktrackerservice.mapper.BookMapper;
import org.project.booktrackerservice.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookTrackerService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDTO createBook(Long id) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId(id);
        bookEntity.setStatus(StatusEnum.FREE);

        BookEntity book = bookRepository.save(bookEntity);
        return bookMapper.toBookDTO(book);
    }

    public void softDeleteBook(Long id) {
        BookEntity bookEntity = bookRepository.findByBookId(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id = " + id + " not found"));
        bookEntity.setDeleted(true);
        bookRepository.save(bookEntity);
    }

    public List<BookDTO> getFreeBooks() {
        List<BookEntity> freeBooks = bookRepository.findAllByStatusFree();
        return bookMapper.toBookDTOList(freeBooks);
    }

    public BookDTO updateStatus(Long id, String status) {
        BookEntity book = bookRepository.findByBookId(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with id = " + id + " not found"));

        book.setStatus(StatusEnum.valueOf(status));

        if (book.getStatus() == StatusEnum.TAKEN) {
            book.setBorrowDate(LocalDateTime.now());
        } else {
            book.setBorrowDate(null);
        }

        bookRepository.save(book);

        return bookMapper.toBookDTO(book);
    }
}
