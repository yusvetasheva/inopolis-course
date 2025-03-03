package com.example.courses.controller;

import com.example.courses.dto.CourseDTO;
import com.example.courses.model.AddCommentToCourseRequest;
import com.example.courses.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/course")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping(value = "/add-course")
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        return new ResponseEntity<>(service.addCourse(courseDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/get-by-name")
    public ResponseEntity<CourseDTO> getCourseByName(@RequestParam String name){
        return new ResponseEntity<>(service.getCourseByName(name), HttpStatus.OK);
    }

    @PostMapping(value = "/add-comment")
    public ResponseEntity<CourseDTO> addCommentToCourse(@Valid @RequestBody AddCommentToCourseRequest request){
        return new ResponseEntity<>(service.addCommentToCourse(request.getCourseName(), request.getCommentText()), HttpStatus.OK);
    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return new ResponseEntity<>(service.getAllCourses(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<CourseDTO> deleteCourseById(@PathVariable Integer id){
        service.deleteCourseById(id);
        return new ResponseEntity<>(service.deleteCourseById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<CourseDTO>updateCourse(@PathVariable Integer id, @RequestBody CourseDTO course){
        return new ResponseEntity<>(service.updateCourse(id, course), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
