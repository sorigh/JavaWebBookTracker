package com.example.demo.model;

import com.example.demo.model.AbstractEntity;
import com.example.demo.model.Book;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Tag extends AbstractEntity {
    @Setter
    @Size(min = 1, max = 25)
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "tags")
    private final List<Book> books = new ArrayList<>();

    public Tag(String name){
        this.name = name;
    }
    public Tag(){}

    public String getDisplayName() {
        return "#" + name + " ";
    }

}
