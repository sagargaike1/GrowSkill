package com.sagar.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    // You can add additional properties like category, duration, etc.

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Class> classes;

    // Getters and setters
}

