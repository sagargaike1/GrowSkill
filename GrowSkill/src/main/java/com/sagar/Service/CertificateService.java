package com.sagar.Service;

import com.sagar.DTO.CertificateDto;
import com.sagar.DTO.CertificateRequestDto;
import com.sagar.Exception.CertificateNotFoundException;
import com.sagar.Exception.EnrollmentNotFoundException;
import com.sagar.Model.Certificate;
import com.sagar.Model.Enrollment;
import com.sagar.Repositories.CertificateRepository;
import com.sagar.Repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Transactional
    public void generateCertificate(CertificateRequestDto requestDto) {
        Enrollment enrollment = enrollmentRepository.findById(requestDto.getEnrollmentId())
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment not found with ID: " + requestDto.getEnrollmentId()));

        Certificate certificate = new Certificate();
        certificate.setEnrollment(enrollment);
        // You can set other properties of the certificate as needed

        certificateRepository.save(certificate);
    }

    @Transactional(readOnly = true)
    public CertificateDto getCertificateById(Long certificateId) {
        Certificate certificate = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new CertificateNotFoundException("Certificate not found with ID: " + certificateId));
        return convertToDto(certificate);
    }

    private CertificateDto convertToDto(Certificate certificate) {
        CertificateDto certificateDto = new CertificateDto();
        certificateDto.setId(certificate.getId());

        return certificateDto;
    }
}
