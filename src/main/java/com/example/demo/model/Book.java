package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Book extends AbstractEntity {
    @Setter
    @NotBlank(message = "Name is required.")
    @Size(min = 3, max = 50, message = "Name too long.")
    private String name;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private BookDetails bookDetails;

    @Setter
    @ManyToOne
    @NotNull(message = "Category is required")
    private BookCategory bookCategory;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    public Book(String name, BookCategory bookCategory) {
        this.name = name;
        this.bookCategory = bookCategory;
    }
    public Book() {}

    public void addTag(Tag tag){
        this.tags.add(tag);
    }
    @Override
    public String toString() {
        return name;
    }

}
