package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class BookCategory extends AbstractEntity {
    @Setter
    @Size(min = 3, max = 50, message = "Name too long.")
    private String name;
    @OneToMany(mappedBy = "bookCategory")
    private final List<Book> books = new ArrayList<>();
    public BookCategory(String name) {
        this.name = name;
    }

    public BookCategory() {

    }

    @Override
    public String toString() {
        return  name;
    }
}
