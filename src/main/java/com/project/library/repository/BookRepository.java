package com.project.library.repository;

import com.project.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Aici definim cautarea dupa categorie (pentru cerinta MVP)
    List<Book> findByCategories_Name(String categoryName);
}