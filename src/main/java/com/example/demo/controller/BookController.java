package com.example.demo.controller;

import com.example.demo.dto.BookTagDTO;
import com.example.demo.model.Book;
import com.example.demo.model.BookCategory;
import com.example.demo.model.BookDetails;
import com.example.demo.model.Tag;
import com.example.demo.repository.BookCategoryRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.TagRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.Errors;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("books")
public class BookController {
    @Autowired
    private BookRepository bookRepository; // we already have findAll, save, findById etc

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @Autowired
    private TagRepository tagRepository;
    @GetMapping
    public String displayAllBooks(@RequestParam(required = false) Integer categoryId, Model model) {
        if (categoryId == null) {
            model.addAttribute("title", "All Books");
            model.addAttribute("books", bookRepository.findAll());
        } else {
            Optional<BookCategory> result = bookCategoryRepository.findById(categoryId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Category ID " + categoryId);
            } else {
                BookCategory category = result.get();
                if (category.getBooks().isEmpty()) {
                    model.addAttribute("title", "No books found in category: " + category.getName());
                } else {
                    model.addAttribute("title", "Books in category: " + category.getName());
                }
                model.addAttribute("books", category.getBooks());
            }
        }
        return "books/index";
    }


    //both live at books/create, it is ok because they handle different things
    @GetMapping("create")
    public String renderCreateBookForm(Model model){
        // Initialize the BookDetails object
        Book newBook = new Book();
        newBook.setBookCategory(new BookCategory());
        newBook.setBookDetails(new BookDetails());  // Make sure bookDetails is not null
        model.addAttribute("title", "Create Book");
        model.addAttribute("book", newBook);
        model.addAttribute("categories", bookCategoryRepository.findAll());
        return "books/create";
    }

    @PostMapping("create")
    public String createBook(@ModelAttribute @Valid Book newBook, Errors errors, Model model){
        if (errors.hasErrors()){
            model.addAttribute("title", "Create Book");
            model.addAttribute("errorMsg", "Bad data!");
            return "books/create"; // Return to the same page to show errors
        }
        bookRepository.save(newBook);
        System.out.println("Current user roles: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return "redirect:/books"; // Redirect to the books list after successful creation
    }


    @GetMapping("delete")
    public String renderDeleteBookForm(Model model){
        model.addAttribute("title", "Delete Book");
        model.addAttribute("books", bookRepository.findAll());

        return "books/delete";
    }

    @PostMapping("delete")
    public String processDeleteBooksForm(@RequestParam(required = false) int[] bookIds) {
        if (bookIds != null) {
            for (int id : bookIds) {
                bookRepository.deleteById(id);
            }
        }
        return "redirect:/books";
    }
    @GetMapping("detail")
    public String displayBookDetails(@RequestParam Integer bookId, Model model) {
        System.out.println("Received bookId: " + bookId);
        Optional<Book> result = bookRepository.findById(bookId);
        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Book ID: " + bookId);
        } else {
            Book book = result.get();
            model.addAttribute("title", book.getName() + " Details");
            model.addAttribute("book", book);
            model.addAttribute("tags", book.getTags());
        }
        return "books/detail";
    }


    //responds to /books/add-tag?bookId=13
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer bookId, Model model){
        Optional<Book> result = bookRepository.findById(bookId);
        Book book = result.get();
        model.addAttribute("title", "Add Tag to:" + book.getName());
        model.addAttribute("tags", tagRepository.findAll());
        BookTagDTO bookTag = new BookTagDTO();
        bookTag.setBook(book);
        model.addAttribute("bookTag",bookTag);
        return "books/add-tag";

    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid BookTagDTO bookTag,
                                    Errors errors, Model model){
        if (!errors.hasErrors()){
            Book book = bookTag.getBook();
            Tag tag = bookTag.getTag();
            if (!book.getTags().contains(tag)){
                book.addTag(tag);
                bookRepository.save(book);
            }
            return "redirect:detail?bookId=" +book.getId();
        }
        return "redirect:add-tag";


    }

}
