package com.project.library.service;

import com.project.library.model.Book;
import com.project.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository; // Baza de date falsa

    @InjectMocks
    private BookService bookService; // Serviciul pe care il testam

    @Test
    void testSaveBook() {
        // 1. Pregatim datele
        Book book = new Book();
        book.setTitle("Test Book");
        book.setStock(10);

        when(bookRepository.save(book)).thenReturn(book);

        // 2. Executam actiunea
        Book savedBook = bookService.saveBook(book);

        // 3. Verificam rezultatul (Assert)
        assertNotNull(savedBook);
        assertEquals("Test Book", savedBook.getTitle());
        assertEquals(10, savedBook.getStock());
    }

    @Test
    void testGetAllBooks() {
        // 1. Pregatim o lista de carti
        Book b1 = new Book();
        Book b2 = new Book();
        when(bookRepository.findAll()).thenReturn(List.of(b1, b2));

        // 2. Executam
        List<Book> books = bookService.getAllBooks();

        // 3. Verificam
        assertEquals(2, books.size());
    }
}