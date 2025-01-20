package org.project.booktrackerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.booktrackerservice.Enum.StatusEnum;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;
    private Long bookId;
    private StatusEnum status;
    private Date borrowDate;
    private Date returnDate;
    private boolean isDeleted = false;

}
