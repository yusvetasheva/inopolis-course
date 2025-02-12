package com.example.courses.mapper;

import com.example.courses.dto.CourseDTO;
import com.example.courses.model.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseEntity dtoToEntity(CourseDTO dto);

    CourseDTO entityToDto(CourseEntity entity);
}
