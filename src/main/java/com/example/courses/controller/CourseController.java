package com.example.courses.controller;

import com.example.courses.model.AddCommentToCourseRequest;
import com.example.courses.model.dto.CourseDTO;
import com.example.courses.service.CourseServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/course")
public class CourseController {
    private final CourseServiceImpl service;

    public CourseController(CourseServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/add-course")
    public Mono<ResponseEntity<CourseDTO>> addCourse(@RequestBody CourseDTO courseDTO) {

        return service.addCourse(courseDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound()
                        .build());
    }

    @GetMapping(value = "/get-by-name")
    public Mono<ResponseEntity<CourseDTO>> getCourseByName(@RequestParam String name) {
        return service.getCourseByName(name)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound()
                        .build());
    }

    @PostMapping(value = "/add-comment")
    public Mono<ResponseEntity<CourseDTO>> addCommentToCourse( @RequestBody AddCommentToCourseRequest request) {

        return service.addCommentToCourse(request.getCourseName(), request.getCommentText())
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<Flux<CourseDTO>> getAllCourses() {
        Flux<CourseDTO> courses = service.getAllCourses();
        return ResponseEntity.ok(courses);
    }


    @DeleteMapping(value = "/delete/{id}")
    public Mono<ResponseEntity<CourseDTO>> deleteCourseById(@PathVariable Integer id) {
        return service.deleteCourseById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound()
                        .build());
    }

    @PutMapping(value = "/update/{id}")
    public Mono<ResponseEntity<CourseDTO>> updateCourse(@PathVariable Integer id, @RequestBody CourseDTO course) {
        return service.updateCourse(id, course).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
