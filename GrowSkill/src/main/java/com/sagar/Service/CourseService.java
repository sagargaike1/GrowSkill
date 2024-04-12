package com.sagar.Service;

import com.sagar.DTO.CourseDto;
import com.sagar.Exception.CourseNotFoundException;
import com.sagar.Exception.InstructorNotFoundException;
import com.sagar.Model.Course;
import com.sagar.Model.Instructor;
import com.sagar.Repositories.CourseRepository;
import com.sagar.Repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Transactional
    public void createCourse(CourseDto courseDto) {
        Instructor instructor = instructorRepository.findById(courseDto.getInstructorId())
                .orElseThrow(() -> new InstructorNotFoundException("Instructor not found with ID: " + courseDto.getInstructorId()));

        Course course = new Course();
        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }

    @Transactional(readOnly = true)
    public CourseDto getCourseById(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + courseId));
        return convertToDto(course);
    }

    @Transactional(readOnly = true)
    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateCourse(Long courseId, CourseDto courseDto) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + courseId));

        Instructor instructor = instructorRepository.findById(courseDto.getInstructorId())
                .orElseThrow(() -> new InstructorNotFoundException("Instructor not found with ID: " + courseDto.getInstructorId()));

        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());
        course.setInstructor(instructor);

        courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + courseId));
        courseRepository.delete(course);
    }

    private CourseDto convertToDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setTitle(course.getTitle());
        courseDto.setDescription(course.getDescription());
        courseDto.setInstructorId(course.getInstructor().getId());
        return courseDto;
    }
}

