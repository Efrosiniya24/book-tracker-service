package org.project.booktrackerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.booktrackerservice.Enum.StatusEnum;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;
    private Long bookId;
    private StatusEnum status;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private boolean isDeleted = false;

}
