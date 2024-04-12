package com.sagar.Controller;

import com.sagar.DTO.ClassDto;
import com.sagar.Service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping("/create")
    public ResponseEntity<?> createClass(@RequestBody ClassDto classDto) {
        try {
            classService.createClass(classDto);
            return ResponseEntity.ok("Class created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating class: " + e.getMessage());
        }
    }

    @GetMapping("/{classId}")
    public ResponseEntity<?> getClassById(@PathVariable Long classId) throws ClassNotFoundException {
        try {
            ClassDto classDto = classService.getClassById(classId);
            return ResponseEntity.ok(classDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving class: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllClasses() {
        try {
            List<ClassDto> classes = classService.getAllClasses();
            return ResponseEntity.ok(classes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving classes: " + e.getMessage());
        }
    }

    @PutMapping("/{classId}")
    public ResponseEntity<?> updateClass(@PathVariable Long classId, @RequestBody ClassDto classDto) {
        try {
            classService.updateClass(classId, classDto);
            return ResponseEntity.ok("Class updated successfully.");
        } catch (ClassNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Class not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating class: " + e.getMessage());
        }
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<?> deleteClass(@PathVariable Long classId) throws ClassNotFoundException {
        try {
            classService.deleteClass(classId);
            return ResponseEntity.ok("Class deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting class: " + e.getMessage());
        }
    }
}

