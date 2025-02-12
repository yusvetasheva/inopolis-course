package com.example.courses.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseDTO {
    String name;
    LocalDate dateBegin;
    Boolean isActive;
}
