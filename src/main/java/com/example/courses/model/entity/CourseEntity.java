package com.example.courses.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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

    public String getName() {
        return name;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public List<String> getComments() {
        return comments;
    }

    // Сеттеры
    public void setName(String name) {
        this.name = name;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
