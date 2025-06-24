package com.example.courses.mapper;

import com.example.courses.model.dto.CourseDTO;
import com.example.courses.model.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseEntity dtoToEntity(CourseDTO dto);

    CourseDTO entityToDto(CourseEntity entity);
}
