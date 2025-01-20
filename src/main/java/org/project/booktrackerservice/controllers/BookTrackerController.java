package org.project.booktrackerservice.controllers;

import lombok.AllArgsConstructor;
import org.project.booktrackerservice.service.BookTrackerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("books/book-tracker")
public class BookTrackerController {
    private final BookTrackerService bookTrackerService;

    @PostMapping("/create-book/{id}")
    public ResponseEntity<String> createBook(@PathVariable Long id){
        bookTrackerService.createBook(id);
        return ResponseEntity.ok("Book with id = " + id + " created");
    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        bookTrackerService.softDeleteBook(id);
        return ResponseEntity.ok("Book with id = " + id + " deleted");
    }

}
