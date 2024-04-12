package com.sagar.Controller;

import com.sagar.DTO.CertificateDto;
import com.sagar.DTO.CertificateRequestDto;
import com.sagar.Exception.CertificateNotFoundException;
import com.sagar.Service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateCertificate(@RequestBody CertificateRequestDto requestDto) {
        try {
            certificateService.generateCertificate(requestDto);
            return ResponseEntity.ok("Certificate generated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error generating certificate: " + e.getMessage());
        }
    }

    @GetMapping("/{certificateId}")
    public ResponseEntity<?> getCertificateById(@PathVariable Long certificateId) {
        try {
            CertificateDto certificateDto = certificateService.getCertificateById(certificateId);
            return ResponseEntity.ok(certificateDto);
        } catch (CertificateNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Certificate not found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving certificate: " + e.getMessage());
        }
    }
}

