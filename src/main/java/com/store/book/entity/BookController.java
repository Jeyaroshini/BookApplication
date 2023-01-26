package com.store.book.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Store>> getAllBooks()
    {
        List<Store> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Store> getBookById(@PathVariable("id") Long id)
    {
        Store store = bookService.findBookById(id);
        return new ResponseEntity<>(store,HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Store> addBook(@RequestBody Store store)
    {
        Store newBook = bookService.addBook(store);
        return  new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}/{title}/{author}/{price}")
    public ResponseEntity<Store> updateBook(@PathVariable("id") Long id,@PathVariable("title") String title,@PathVariable("author") String author,@PathVariable("price") Double price, @RequestBody Store store)
    {
        Store updatedBook = bookService.findBookById(id);
        updatedBook.setAuthor(author);
        updatedBook.setTitle(title);
        updatedBook.setPrice(price);
        bookService.addBook(updatedBook);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)
    {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
