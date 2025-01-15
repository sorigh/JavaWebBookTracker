package com.example.demo.controller;

import com.example.demo.model.BookCategory;
import com.example.demo.repository.BookCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("categories")
public class BookCategoryController {

    @Autowired
    private BookCategoryRepository bookCategoryRepository;

    @GetMapping
    public String displayAllCategories(Model model) {
        model.addAttribute("title", "All Categories");
        model.addAttribute("categories", bookCategoryRepository.findAll());
        return "categories/index";
    }

    @GetMapping("create")
    public String renderCreateCategoryForm(Model model) {
        model.addAttribute("title", "Create Book Category");
        model.addAttribute(new BookCategory());
        return "categories/create";
    }

    @PostMapping("create")
    public String processCreateCategoryForm(@ModelAttribute @Valid BookCategory bookCategory,
                                            Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Book Category");
            return "categories/create";
        }
        bookCategoryRepository.save(bookCategory);
        return "redirect:/books";
    }

    @PostMapping("delete")
    public String processDeleteCategories(@RequestParam(required = false) int[] categoryIds) {
        if (categoryIds != null) {
            for (int id : categoryIds) {
                bookCategoryRepository.deleteById(id);
            }
        }
        return "redirect:/categories";
    }
}
