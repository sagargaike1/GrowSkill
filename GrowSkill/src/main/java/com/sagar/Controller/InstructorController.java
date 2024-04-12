package com.sagar.Controller;

import com.sagar.DTO.InstructorDto;
import com.sagar.Exception.InstructorNotFoundException;
import com.sagar.Service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping("/create")
    public ResponseEntity<?> createInstructor(@RequestBody InstructorDto instructorDto) {
        try {
            instructorService.createInstructor(instructorDto);
            return ResponseEntity.ok("Instructor created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating instructor: " + e.getMessage());
        }
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<?> getInstructorById(@PathVariable Long instructorId) {
        try {
            InstructorDto instructorDto = instructorService.getInstructorById(instructorId);
            return ResponseEntity.ok(instructorDto);
        } catch (InstructorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Instructor not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving instructor: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllInstructors() {
        try {
            List<InstructorDto> instructors = instructorService.getAllInstructors();
            return ResponseEntity.ok(instructors);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving instructors: " + e.getMessage());
        }
    }

    @PutMapping("/{instructorId}")
    public ResponseEntity<?> updateInstructor(@PathVariable Long instructorId, @RequestBody InstructorDto instructorDto) {
        try {
            instructorService.updateInstructor(instructorId, instructorDto);
            return ResponseEntity.ok("Instructor updated successfully.");
        } catch (InstructorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Instructor not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating instructor: " + e.getMessage());
        }
    }

    @DeleteMapping("/{instructorId}")
    public ResponseEntity<?> deleteInstructor(@PathVariable Long instructorId) {
        try {
            instructorService.deleteInstructor(instructorId);
            return ResponseEntity.ok("Instructor deleted successfully.");
        } catch (InstructorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Instructor not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting instructor: " + e.getMessage());
        }
    }
}

