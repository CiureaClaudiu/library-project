package com.project.library.service;

import com.project.library.model.Book;
import com.project.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    // Metoda simpla de salvare
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    // Metoda de afisare a tuturor cartilor
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Cautare dupa categorie (pentru MVP)
    public List<Book> getBooksByCategory(String categoryName) {
        return bookRepository.findByCategories_Name(categoryName);
    }
}