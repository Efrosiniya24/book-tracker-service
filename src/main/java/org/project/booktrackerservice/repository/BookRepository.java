package org.project.booktrackerservice.repository;

import org.project.booktrackerservice.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
