package com.sagar.Service;

import com.sagar.DTO.EnrollmentDto;
import com.sagar.Exception.CourseNotFoundException;
import com.sagar.Exception.EnrollmentNotFoundException;
import com.sagar.Exception.UserNotFoundException;
import com.sagar.Model.Course;
import com.sagar.Model.Enrollment;
import com.sagar.Model.User;
import com.sagar.Repositories.CourseRepository;
import com.sagar.Repositories.EnrollmentRepository;
import com.sagar.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public void enrollUserToCourse(EnrollmentDto enrollmentDto) {
        User user = userRepository.findById(enrollmentDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + enrollmentDto.getUserId()));

        Course course = courseRepository.findById(enrollmentDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + enrollmentDto.getCourseId()));

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);

        enrollmentRepository.save(enrollment);
    }

    @Transactional(readOnly = true)
    public EnrollmentDto getEnrollmentById(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with ID: " + enrollmentId));
        return convertToDto(enrollment);
    }

    @Transactional
    public void cancelEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with ID: " + enrollmentId));
        enrollmentRepository.delete(enrollment);
    }

    private EnrollmentDto convertToDto(Enrollment enrollment) {
        EnrollmentDto enrollmentDto = new EnrollmentDto();
        enrollmentDto.setId(enrollment.getId());
        enrollmentDto.setUserId(enrollment.getUser().getId());
        enrollmentDto.setCourseId(enrollment.getCourse().getId());
        return enrollmentDto;
    }
}
