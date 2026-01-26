package com.notes.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NoteDTO {
    private Long id;
    private String title;
    private String note;
    private LocalDate creationDate;
    private LocalDate endingDate;
    private Long userId;
}
