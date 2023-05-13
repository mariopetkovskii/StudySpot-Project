package com.example.studyspotbackend.models.course.helpers;

import lombok.Data;
import lombok.Getter;

@Data
public class LessonDto {
    String name;
    String desc;
    String url;
    Long id;
    String name_mk;
    String desc_mk;
}
