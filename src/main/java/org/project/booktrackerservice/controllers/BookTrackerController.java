package org.project.booktrackerservice.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.project.booktrackerservice.dto.BookDTO;
import org.project.booktrackerservice.service.BookTrackerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("books/book-tracker")
public class BookTrackerController {
    private final BookTrackerService bookTrackerService;

    @PostMapping("/create-book/{id}")
    public ResponseEntity<BookDTO> createBook(@PathVariable Long id) {
        BookDTO bookDTO = bookTrackerService.createBook(id);
        return ResponseEntity.ok(bookDTO);
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookTrackerService.softDeleteBook(id);
        return ResponseEntity.ok("Book with id = " + id + " deleted");
    }

    @GetMapping("/free-books")
    public ResponseEntity<List<BookDTO>> getFreeBooks() {
        List<BookDTO> freeBooks = bookTrackerService.getFreeBooks();
        return ResponseEntity.ok(freeBooks);
    }

    @PatchMapping("/update-status/{id}")
    public ResponseEntity<BookDTO> updateBookStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            BookDTO book = bookTrackerService.updateStatus(id, status);
            return ResponseEntity.ok(book);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
