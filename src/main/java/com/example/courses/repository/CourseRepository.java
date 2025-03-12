package com.example.courses.repository;

import com.example.courses.model.entity.CourseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface CourseRepository extends ReactiveCrudRepository<CourseEntity, Integer> {
    Mono<CourseEntity> findByName(String name);
}
