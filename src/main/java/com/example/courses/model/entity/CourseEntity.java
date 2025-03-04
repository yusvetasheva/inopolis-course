package com.example.courses.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "course_new")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "date_begin")
    LocalDate dateBegin;
    @Column(name = "is_active")
    Boolean isActive;
    @ElementCollection
    @CollectionTable(name = "course_comment", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "comment_rating")
    List<String> comments = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (isActive == null) {
            isActive = true;
        }
    }
}
