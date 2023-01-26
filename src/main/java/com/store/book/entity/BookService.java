package com.store.book.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    private final BookRepo bookRepo;
    @Autowired
    public BookService(BookRepo bookRepo)
    {
        this.bookRepo = bookRepo;
    }

    public Store addBook(Store store)
    {
        return bookRepo.save(store);
    }

    public List<Store> getAllBooks()
    {
        return bookRepo.findAll();
    }

    public Store findBookById(Long id)
    {
        return bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException("product with id "+id+" not found"));
    }

    public void delete(Long id)
    {
        bookRepo.deleteById(id);
    }
}
