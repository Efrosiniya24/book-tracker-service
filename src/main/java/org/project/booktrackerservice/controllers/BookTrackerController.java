package org.project.booktrackerservice.controllers;

import lombok.AllArgsConstructor;
import org.project.booktrackerservice.service.BookTrackerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
