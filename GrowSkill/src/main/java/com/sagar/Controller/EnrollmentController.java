package com.sagar.Controller;

import com.sagar.DTO.EnrollmentDto;
import com.sagar.Exception.EnrollmentNotFoundException;
import com.sagar.Service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/enroll")
    public ResponseEntity<?> enrollUserToCourse(@RequestBody EnrollmentDto enrollmentDto) {
        try {
            enrollmentService.enrollUserToCourse(enrollmentDto);
            return ResponseEntity.ok("User enrolled to course successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error enrolling user to course: " + e.getMessage());
        }
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<?> getEnrollmentById(@PathVariable Long enrollmentId) {
        try {
            EnrollmentDto enrollmentDto = enrollmentService.getEnrollmentById(enrollmentId);
            return ResponseEntity.ok(enrollmentDto);
        } catch (EnrollmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Enrollment not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving enrollment: " + e.getMessage());
        }
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<?> cancelEnrollment(@PathVariable Long enrollmentId) {
        try {
            enrollmentService.cancelEnrollment(enrollmentId);
            return ResponseEntity.ok("Enrollment cancelled successfully.");
        } catch (EnrollmentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Enrollment not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error cancelling enrollment: " + e.getMessage());
        }
    }
}
