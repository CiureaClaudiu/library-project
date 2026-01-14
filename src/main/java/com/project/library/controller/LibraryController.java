package com.project.library.controller;

import com.project.library.dto.BookRequest;
import com.project.library.dto.LoanRequest;
import com.project.library.dto.MemberRequest;
import com.project.library.model.Book;
import com.project.library.model.Loan;
import com.project.library.model.Member;
import com.project.library.repository.MemberRepository;
import com.project.library.service.BookService;
import com.project.library.service.LendingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LibraryController {

    private final BookService bookService;
    private final LendingService lendingService;
    private final MemberRepository memberRepository; // Il folosim direct pt simplitate la Member

    // 1. Endpoint adaugare carte (POST)
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody @Valid BookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPublicationYear(request.getPublicationYear());
        book.setStock(request.getStock());
        // Nota: Aici am putea cauta si seta autorul/editura dupa ID,


        return ResponseEntity.ok(bookService.saveBook(book));
    }

    // 2. Endpoint lista carti (GET)
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // 3. Endpoint inregistrare membru (POST)
    @PostMapping("/members")
    public ResponseEntity<Member> registerMember(@RequestBody @Valid MemberRequest request) {
        Member member = new Member();
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setEmail(request.getEmail());
        member.setPhoneNumber(request.getPhoneNumber());

        return ResponseEntity.ok(memberRepository.save(member));
    }

    // 4. Endpoint IMPRUMUT (POST)
    @PostMapping("/borrow")
    public ResponseEntity<Loan> borrowBook(@RequestBody @Valid LoanRequest request) {
        Loan loan = lendingService.borrowBook(request.getBookId(), request.getMemberId());
        return ResponseEntity.ok(loan);
    }

    // 5. Endpoint cautare dupa categorie (GET)
    @GetMapping("/books/search")
    public ResponseEntity<List<Book>> searchByCategory(@RequestParam String category) {
        return ResponseEntity.ok(bookService.getBooksByCategory(category));
    }
}