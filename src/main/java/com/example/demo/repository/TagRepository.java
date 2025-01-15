package com.example.demo.repository;

import com.example.demo.model.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository  extends CrudRepository<Tag,Integer> {
}
