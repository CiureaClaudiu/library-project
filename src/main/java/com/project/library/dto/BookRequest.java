package com.project.library.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "ISBN is required")
    private String isbn;

    @NotNull(message = "Publication year is required")
    private Integer publicationYear;

    @Min(value = 1, message = "Stock must be at least 1") // Validare stoc minim
    private Integer stock;

    private Long authorId;     // Primim doar ID-ul, nu tot obiectul
    private Long publisherId;
}