package com.example.courses.repository;

import com.example.courses.model.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**/
@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    Optional<CourseEntity> findByName(String name);
}
