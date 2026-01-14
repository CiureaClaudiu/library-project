package com.project.library.service;

import com.project.library.exception.BookNotAvailableException;
import com.project.library.model.Book;
import com.project.library.model.Loan;
import com.project.library.model.Member;
import com.project.library.repository.BookRepository;
import com.project.library.repository.LoanRepository;
import com.project.library.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LendingService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Loan borrowBook(Long bookId, Long memberId) {
        // 1. Cautam cartea
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // 2. Cautam membrul
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 3. Validare: Verificam stocul
        if (book.getStock() <= 0) {
            throw new BookNotAvailableException("Cartea '" + book.getTitle() + "' nu mai este pe stoc!");
        }

        // 4. Scadem stocul
        book.setStock(book.getStock() - 1);
        bookRepository.save(book);

        // 5. Cream imprumutul
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setMember(member);
        loan.setLoanDate(LocalDateTime.now());
        loan.setDueDate(LocalDateTime.now().plusDays(14)); // 2 saptamani termen
        loan.setStatus("ACTIVE");

        // 6. Salvam totul
        return loanRepository.save(loan);
    }
}