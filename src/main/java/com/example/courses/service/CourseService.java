package com.example.courses.service;

import com.example.courses.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    CourseDTO getCourseByName(String name);

    List<CourseDTO> getAllCourses();

    CourseDTO addCourse(CourseDTO course);

    CourseDTO deleteCourseById (Integer id);

    CourseDTO updateCourse(Integer id, CourseDTO courseDTO);

    CourseDTO addCommentToCourse(String courseName, String comment);
}
