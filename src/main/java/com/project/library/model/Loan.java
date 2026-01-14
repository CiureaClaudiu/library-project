package com.project.library.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime loanDate = LocalDateTime.now();
    private LocalDateTime returnDate; // E null pana cand o returneaza
    private LocalDateTime dueDate;    // Data scadenta

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    // Status: ACTIVE, RETURNED
    private String status;
}