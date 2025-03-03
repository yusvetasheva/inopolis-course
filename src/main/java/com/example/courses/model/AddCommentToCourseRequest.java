package com.example.courses.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddCommentToCourseRequest {
    @NotBlank(message = "Поле courseName не может быть пустым")
    String courseName;
    @NotBlank(message = "Поле commentText не может быть пустым")
    String commentText;
}
