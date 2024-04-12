package com.sagar.Service;

import com.sagar.DTO.ClassDto;
import com.sagar.Exception.CourseNotFoundException;
import com.sagar.Model.Class;
import com.sagar.Model.Course;
import com.sagar.Repositories.ClassRepository;
import com.sagar.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public void createClass(ClassDto classDto) {
        Course course = courseRepository.findById(classDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + classDto.getCourseId()));

        Class aClass = new Class();
        aClass.setDateTime(aClass.getDateTime());
        aClass.setTopic(classDto.getTopic());
        aClass.setZoomLink(classDto.getZoomLink());
        aClass.setCourse(course);

        classRepository.save(aClass);
    }

    @Transactional(readOnly = true)
    public ClassDto getClassById(Long classId) {
        Class aClass = null;
        try {
            aClass = classRepository.findById(classId)
                    .orElseThrow(() -> new ClassNotFoundException("Class not found with ID: " + classId));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return convertToDto(aClass);
    }

    @Transactional(readOnly = true)
    public List<ClassDto> getAllClasses() {
        List<Class> classes = classRepository.findAll();
        return classes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateClass(Long classId, ClassDto classDto) throws ClassNotFoundException {
        Class aClass = classRepository.findById(classId)
                .orElseThrow(() -> new ClassNotFoundException("Class not found with ID: " + classId));
        Course course = courseRepository.findById(classDto.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + classDto.getCourseId()));

        aClass.setDateTime(aClass.getDateTime());
        aClass.setTopic(classDto.getTopic());
        aClass.setZoomLink(classDto.getZoomLink());
        aClass.setCourse(course);

        classRepository.save(aClass);
    }

    @Transactional
    public void deleteClass(Long classId) {
        Class aClass = null;
        try {
            aClass = classRepository.findById(classId)
                    .orElseThrow(() -> new ClassNotFoundException("Class not found with ID: " + classId));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        classRepository.delete(aClass);
    }

    private ClassDto convertToDto(Class aClass) {
        ClassDto classDto = new ClassDto();
        classDto.setId(aClass.getId());
        classDto.setDateTime(aClass.getDateTime());
        classDto.setTopic(aClass.getTopic());
        classDto.setZoomLink(aClass.getZoomLink());
        classDto.setCourseId(aClass.getCourse().getId());
        return classDto;
    }
}

