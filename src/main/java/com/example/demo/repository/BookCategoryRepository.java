package com.example.demo.repository;

import com.example.demo.model.BookCategory;
import org.springframework.data.repository.CrudRepository;

public interface BookCategoryRepository extends CrudRepository<BookCategory, Integer> {
}
