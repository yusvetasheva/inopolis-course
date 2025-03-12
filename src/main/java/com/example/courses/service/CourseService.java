package com.example.courses.service;

import com.example.courses.model.dto.CourseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CourseService {
    Mono<CourseDTO> getCourseByName(String name);

    Flux<CourseDTO> getAllCourses();

    Mono<CourseDTO> addCourse(CourseDTO course);

    Mono<CourseDTO> deleteCourseById (Integer id);

    Mono<CourseDTO> updateCourse(Integer id, CourseDTO courseDTO);

    Mono<CourseDTO> addCommentToCourse(String courseName, String comment);
}
