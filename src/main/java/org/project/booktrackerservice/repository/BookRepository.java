package org.project.booktrackerservice.repository;

import org.project.booktrackerservice.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
   Optional<BookEntity> findByBookId(Long id);
}
