package com.sagar.DTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentDto {
    private Long id;
    private Long userId;
    private Long courseId;

}
