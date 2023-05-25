package com.example.studyspotbackend.models.course.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String name_mk;
    @Column(length = 2000)
    private String description;
    @Column(length = 2000)
    private String description_mk;

    private String url;
    public Lesson(String name, String description, String url, String name_mk, String description_mk) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.name_mk = name_mk;
        this.description_mk = description_mk;
    }

    public Lesson() {

    }
}
