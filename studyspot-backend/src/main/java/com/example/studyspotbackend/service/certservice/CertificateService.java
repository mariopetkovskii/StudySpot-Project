package com.example.studyspotbackend.service.certservice;

import com.example.studyspotbackend.helperfunctinos.HelperFunction;
import com.example.studyspotbackend.models.course.entity.Course;
import com.example.studyspotbackend.models.course.exceptions.CertificateCanNotBeGeneratedException;
import com.example.studyspotbackend.models.course.exceptions.CourseNotFoundException;
import com.example.studyspotbackend.models.course.helpers.CourseDto;
import com.example.studyspotbackend.models.user.entity.User;
import com.example.studyspotbackend.repository.course.CourseRepository;
import com.example.studyspotbackend.repository.user.UserRepository;
import com.example.studyspotbackend.service.quiz.interfaces.QuizResultsService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Service
@AllArgsConstructor
public class CertificateService {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final QuizResultsService quizResultsService;

    public void export(HttpServletResponse response, String jwtToken, CourseDto courseDto) throws IOException {
        User user = this.userRepository.findByEmail(HelperFunction.decodeJwtToGetEmail(jwtToken));
        Course course = this.courseRepository.findById(courseDto.getId()).orElseThrow(CourseNotFoundException::new);
        if(!quizResultsService.canGenerateCert(user, course)){
            throw new CertificateCanNotBeGeneratedException();
        }
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        String fullName = user.getFirstName() + " " + user.getLastName();

        String certificateFor = "Certificate for " + fullName;

        Paragraph paragraph = new Paragraph(certificateFor, fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);


        String pointsInCert = "You have passed your exam";

        Paragraph paragraph2 = new Paragraph(pointsInCert, fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph2);
        document.close();
    }
}
