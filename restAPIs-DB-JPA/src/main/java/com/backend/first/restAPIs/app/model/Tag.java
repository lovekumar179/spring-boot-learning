package com.backend.first.restAPIs.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @ManyToMany(mappedBy = "tags")
  @JsonIgnore
  private List<Post> posts;

  public Tag() {
  }

  public Tag(String name) {
    this.name = name;
  }

  
}

