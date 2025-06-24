package com.example.courses.service;

import com.example.courses.model.dto.CourseDTO;
import com.example.courses.mapper.CourseMapper;
import com.example.courses.repository.CourseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseServiceImpl implements CourseService {

    CourseRepository repository;

    CourseMapper mapper;

    @Override
    public Mono<CourseDTO> getCourseByName(String name) {
        if (name == null || name.isEmpty()) {
            return Mono.error(new IllegalArgumentException("name в методе getCourseByName не может быть null/empty"));
        }

        return repository.findByName(name.trim())  // Метод должен возвращать Mono<CourseEntity>
                .map(mapper::entityToDto)  // Преобразуем CourseEntity -> CourseDTO
                .switchIfEmpty(Mono.error(new NoSuchElementException("Нет курса с названием = " + name)));
    }


    @Override
    public Flux<CourseDTO> getAllCourses() {
        return repository.findAll().map(mapper::entityToDto);
    }

    @Override
    public Mono<CourseDTO> addCourse(CourseDTO course) {
        if (course == null || course.getName() == null || course.getName().isEmpty())
            return Mono.error(new IllegalArgumentException("course или course.name не могут быть null в методе addCourse"));

        return repository.save(mapper.dtoToEntity(course)).map(mapper::entityToDto);

    }

    /**
     * По ТЗ курс нужно не удалять, а переводить в состояние isActive = false
     */
    @Override
    public Mono<CourseDTO> deleteCourseById(Integer id) {
        if (id == null)
            return Mono.error(new IllegalArgumentException("id не может быть null в методе deleteCourseById"));

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NoSuchElementException("В БД не курса с id = " + id)))
                .flatMap(course -> {
                    course.setIsActive(false);
                    return repository.save(course);
                })
                .map(mapper::entityToDto);

    }

    @Override
    public Mono<CourseDTO> updateCourse(Integer id, CourseDTO course) {
        if (course == null || course.getName() == null || course.getName().isEmpty() || id == null)
            return Mono.error(new IllegalArgumentException("Некорректные аргументы в методе updateCourse"));

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new NoSuchElementException("В БД не курса с id = " + id)))
                .flatMap(exist -> {
                    exist.setName(course.getName());
                    exist.setDateBegin(course.getDateBegin());

                    return repository.save(exist);
                })
                .map(mapper::entityToDto);

    }

    @Override
    public Mono<CourseDTO> addCommentToCourse(String courseName, String comment) {

        return repository.findByName(courseName)
                .switchIfEmpty(Mono.error(new NoSuchElementException("Нет курса с названием  = " + courseName)))
                .flatMap(course -> {
                    course.getComments().add(comment);

                    return repository.save(course);
                })
                .map(mapper::entityToDto);
    }
}
