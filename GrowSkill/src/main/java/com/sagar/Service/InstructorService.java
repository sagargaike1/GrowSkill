package com.sagar.Service;

import com.sagar.DTO.InstructorDto;
import com.sagar.Exception.InstructorNotFoundException;
import com.sagar.Model.Instructor;
import com.sagar.Repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Transactional
    public void createInstructor(InstructorDto instructorDto) {
        Instructor instructor = new Instructor();
        instructor.setName(instructorDto.getName());

        instructorRepository.save(instructor);
    }

    @Transactional(readOnly = true)
    public InstructorDto getInstructorById(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new InstructorNotFoundException("Instructor not found with ID: " + instructorId));
        return convertToDto(instructor);
    }

    @Transactional(readOnly = true)
    public List<InstructorDto> getAllInstructors() {
        List<Instructor> instructors = instructorRepository.findAll();
        return instructors.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateInstructor(Long instructorId, InstructorDto instructorDto) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new InstructorNotFoundException("Instructor not found with ID: " + instructorId));
        instructor.setName(instructorDto.getName());
        // Update other fields as needed

        instructorRepository.save(instructor);
    }

    @Transactional
    public void deleteInstructor(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new InstructorNotFoundException("Instructor not found with ID: " + instructorId));
        instructorRepository.delete(instructor);
    }

    private InstructorDto convertToDto(Instructor instructor) {
        InstructorDto instructorDto = new InstructorDto();
        instructorDto.setId(instructor.getId());
        instructorDto.setName(instructor.getName());

        return instructorDto;
    }
}

