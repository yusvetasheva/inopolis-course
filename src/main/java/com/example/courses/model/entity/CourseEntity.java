package com.example.courses.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Table(name = "course_flux")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseEntity {
    @Id
    @Column("id")
    Integer id;
    @Column("name")
    String name;
    @Column("date_begin")
    LocalDate dateBegin;
    @Column("is_active")
    Boolean isActive;
    @Column("comments")
    List<String> comments = new ArrayList<>();

    public void prePersist() {
        if (isActive == null) {
            isActive = true;
        }
    }
}
