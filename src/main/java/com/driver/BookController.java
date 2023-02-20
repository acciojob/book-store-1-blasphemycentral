package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
//        bookList.add(null);
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book)
    {
        // Your code goes here.
        book.setId(id);
        bookList.add(book);
        this.id++;
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()
    @GetMapping("/get-book-by-id/{id}")
    public Book getBookById(@PathVariable("id") int id)
    {
        return bookList.get(id-1);
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("/delete-book-by-id/{id}")
    public String deleteBookById(@PathVariable("id") int id)
    {
        if(bookList.isEmpty()) return "Database empty";
        else if(id>=bookList.size()) return "Book not available";
        bookList.remove(id);
        return "Successfully deleted";
    }

    // get request /get-all-books
    // getAllBooks()
    @GetMapping("/get-all-books")
    public List<Book> getAllBooks()
    {
        return getBookList();
    }

    // delete request /delete-all-books
    // deleteAllBooks()
    @DeleteMapping
    public String deleteAllBooks()
    {
        bookList.clear();
        return "Successfully deleted all books";
    }

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam("author") String author)
    {
        List<Book> temp = new ArrayList<>();
        for(Book b : bookList)
        {
            if(b.getAuthor().equals(author)) temp.add(b);
        }
        return new ResponseEntity<>(temp, HttpStatus.FOUND);
    }

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    @GetMapping("/get-books-by-genre")
    public List<Book> getBooksByGenre(@RequestParam("genre") String genre)
    {
        List<Book> temp = new ArrayList<>();
        for(Book b : bookList)
        {
            if(b.getGenre().equals(genre)) temp.add(b);
        }
        return temp;
    }
}
