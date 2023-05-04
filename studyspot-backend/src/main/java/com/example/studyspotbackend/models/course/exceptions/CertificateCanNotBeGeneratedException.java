package com.example.studyspotbackend.models.course.exceptions;

public class CertificateCanNotBeGeneratedException extends RuntimeException{
    public CertificateCanNotBeGeneratedException() {
        super("Certification can not be generated");
    }
}
