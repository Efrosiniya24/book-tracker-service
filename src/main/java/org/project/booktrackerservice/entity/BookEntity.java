package org.project.booktrackerservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project.booktrackerservice.Enum.StatusEnum;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_tracker")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private LocalDateTime borrowDate;

    private LocalDateTime returnDate;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
