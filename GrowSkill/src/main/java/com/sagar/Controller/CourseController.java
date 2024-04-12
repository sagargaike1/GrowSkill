package com.sagar.Controller;

import com.sagar.DTO.CourseDto;
import com.sagar.Exception.CourseNotFoundException;
import com.sagar.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto) {
        try {
            courseService.createCourse(courseDto);
            return ResponseEntity.ok("Course created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating course: " + e.getMessage());
        }
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable Long courseId) {
        try {
            CourseDto courseDto = courseService.getCourseById(courseId);
            return ResponseEntity.ok(courseDto);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving course: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCourses() {
        try {
            List<CourseDto> courses = courseService.getAllCourses();
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving courses: " + e.getMessage());
        }
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @RequestBody CourseDto courseDto) {
        try {
            courseService.updateCourse(courseId, courseDto);
            return ResponseEntity.ok("Course updated successfully.");
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating course: " + e.getMessage());
        }
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        try {
            courseService.deleteCourse(courseId);
            return ResponseEntity.ok("Course deleted successfully.");
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Course not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting course: " + e.getMessage());
        }
    }
}

