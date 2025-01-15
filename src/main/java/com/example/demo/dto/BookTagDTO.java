package com.example.demo.dto;

import com.example.demo.model.Book;
import com.example.demo.model.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookTagDTO {
    @NotNull
    private Book book;
    @NotNull
    private Tag tag;
    public BookTagDTO(){}

}
