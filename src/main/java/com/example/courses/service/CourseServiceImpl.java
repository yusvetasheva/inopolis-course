package com.example.courses.service;

import com.example.courses.dto.CourseDTO;
import com.example.courses.mapper.CourseMapper;
import com.example.courses.model.CourseEntity;
import com.example.courses.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repository;

    private final CourseMapper mapper = CourseMapper.INSTANCE;

    public CourseServiceImpl(CourseRepository repository) {
        this.repository = repository;
    }

    @Override
    public CourseDTO getCourseByName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("name в методе getCourseByName не может быть null/empty");

        Optional<CourseEntity> entity = repository.findByName(name.trim());
        if (entity.isPresent()) return mapper.entityToDto(entity.get());
        else throw new NoSuchElementException("Нет курса с названием = " + name);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return repository.findAll().stream().map(mapper::entityToDto).toList();
    }

    @Override
    public CourseDTO addCourse(CourseDTO course) {
        if (course == null || course.getName() == null || course.getName().isEmpty())
            throw new IllegalArgumentException("course или course.name не могут быть null в методе addCourse");
        repository.save(mapper.dtoToEntity(course));
        return course;
    }

    /**
     * По ТЗ курс нужно не удалять, а переводить в состояние isActive = false
     */
    @Override
    public CourseDTO deleteCourseById(Integer id) {
        if (id == null)
            throw new IllegalArgumentException("id не может быть null в методе deleteCourseById");
        CourseEntity existEntity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("В БД не курса с id = " + id));

        existEntity.setIsActive(false);
        repository.save(existEntity);

        return mapper.entityToDto(existEntity);
    }

    @Override
    public CourseDTO updateCourse(Integer id, CourseDTO course) {
        if (course == null || course.getName() == null || course.getName().isEmpty() || id == null)
            throw new IllegalArgumentException("Некорректные аргументы в методе updateCourse");
        CourseEntity existEntity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("В БД не курса с id = " + id));

        existEntity.setName(course.getName());
        existEntity.setDateBegin(course.getDateBegin());

        repository.save(existEntity);

        return mapper.entityToDto(existEntity);
    }

    @Override
    public CourseDTO addCommentToCourse(String courseName, String comment) {

        Optional<CourseEntity> existEntity = repository.findByName(courseName);

        if (existEntity.isEmpty()) throw new NoSuchElementException("Нет курса с названием  = " + courseName);

        existEntity.get().getComments().add(comment);

        repository.save(existEntity.get());

        return mapper.entityToDto(existEntity.get());
    }
}
