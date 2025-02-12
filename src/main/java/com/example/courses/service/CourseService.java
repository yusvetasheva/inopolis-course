package com.example.courses.service;

import com.example.courses.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    CourseDTO getCourseByName(String name);

    List<CourseDTO> getAllCourses();

    void addCourse(CourseDTO course);

    void deleteCourseById (Integer id);

    void updateCourse(Integer id, CourseDTO courseDTO);
}
