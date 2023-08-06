package com.clab.securecoding.type.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String species;

    private Long age;

    private String gender;

    private String specialNotes;

    private String vaccine;

    private Boolean isNeutered;

    private String reasonForAdoption;

    private String previousHomeEnvironment;

    private String likes;

    private String dislikes;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
