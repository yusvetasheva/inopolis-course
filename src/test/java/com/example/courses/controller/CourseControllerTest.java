package com.example.courses.controller;

import com.example.courses.dto.CourseDTO;
import com.example.courses.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CourseService courseService;

    @Test
    public void addCourseSuccess() throws Exception {

        when(courseService.addCourse(any())).thenReturn(getCourseDto());

        mockMvc.perform(post("/api/course/add-course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getCourseDto())))
                .andExpect(status().isOk());

        verify(courseService).addCourse(any());

    }

    @Test
    public void addCourseNullNameError() throws Exception {

        mockMvc.perform(post("/api/course/add-course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().is4xxClientError());

        verify(courseService, never()).addCourse(any());

    }

    @Test
    public void deleteByIdSuccess() throws Exception {

        when(courseService.deleteCourseById(any())).thenReturn(getCourseDto());

        mockMvc.perform(delete("/api/course/delete/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(courseService, times(2)).deleteCourseById(any());
    }

    @Test
    public void updateCourseSuccess() throws Exception {
        when(courseService.updateCourse(any(), any())).thenReturn(getCourseDto());

        mockMvc.perform(put("/api/course/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getCourseDto())))
                .andExpect(status().isOk());

        verify(courseService).updateCourse(any(), any());
    }

    @Test
    public void getCourseByNameSuccess() throws Exception {

        when(courseService.getCourseByName(any())).thenReturn(getCourseDto());

        mockMvc.perform(get("/api/course/get-by-name")
                        .param("name", "test_name")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test_name"))
                .andExpect(jsonPath("$.isActive").value(true))
                .andExpect(jsonPath("$.dateBegin").value(LocalDate.now().toString()));

        verify(courseService).getCourseByName(any());
    }

    @Test
    public void getAllCoursesSuccess() throws Exception {
        when(courseService.getAllCourses()).thenReturn(List.of(getCourseDto()));

        mockMvc.perform(get("/api/course/get-all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("test_name"))
                .andExpect(jsonPath("$[0].isActive").value(true))
                .andExpect(jsonPath("$[0].dateBegin").value(LocalDate.now().toString()));

        verify(courseService).getAllCourses();
    }


    CourseDTO getCourseDto() {
        return CourseDTO.builder()
                .name("test_name")
                .isActive(true)
                .dateBegin(LocalDate.now())
                .build();
    }
}
