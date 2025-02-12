package com.example.courses.controller;

import com.example.courses.dto.CourseDTO;
import com.example.courses.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/course")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @PostMapping(value = "/add-course")
    public ResponseEntity<String> addCourse(@RequestBody CourseDTO courseDTO) {
        service.addCourse(courseDTO);
        return ResponseEntity.ok("Курс успешно добавлен");
    }

    @GetMapping(value = "/get-by-name")
    public ResponseEntity<CourseDTO> getCourseByName(@RequestParam String name){
        return ResponseEntity.of(Optional.of(service.getCourseByName(name)));

    }

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return ResponseEntity.of(Optional.of(service.getAllCourses()));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Integer id){
        service.deleteCourseById(id);
        return ResponseEntity.ok("Курс с id = " + id + " успешно удален");
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String>updateCourse(@PathVariable Integer id, @RequestBody CourseDTO course){
        service.updateCourse(id, course);
        return ResponseEntity.ok("Курс с id = " + id + " успешно обновлен");
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
