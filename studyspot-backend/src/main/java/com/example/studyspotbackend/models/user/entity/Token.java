package com.example.studyspotbackend.models.user.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Column(name = "date_created")
    private OffsetDateTime dateCreated;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Column(name = "expiration_date")
    private OffsetDateTime expirationDate;
}
