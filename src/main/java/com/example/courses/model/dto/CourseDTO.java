package com.example.courses.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDTO {
    String name;
    LocalDate dateBegin;
    Boolean isActive;
    List<String> comments;

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
