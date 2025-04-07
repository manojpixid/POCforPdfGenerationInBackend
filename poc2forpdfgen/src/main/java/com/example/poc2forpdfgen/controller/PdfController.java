package com.example.poc2forpdfgen.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import com.example.poc2forpdfgen.service.PdfGeneratorService;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    private final PdfGeneratorService pdfGeneratorService;

    public PdfController(PdfGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/generate")
    public ResponseEntity<byte[]> generatePdf() {
        Context context = new Context();
        context.setVariable("date", "06/04/2025");
        context.setVariable("firstName", "Pixid");
        context.setVariable("lastName", "Ai");
        context.setVariable("businessName", "PIXID AI");

        context.setVariable("abn", "123232233232");
        context.setVariable("physicalAddress", "Australia");
        context.setVariable("contactPhoneNo", "123456789");
        context.setVariable("emailAddress", "pixid.ai@mail.com");
        context.setVariable("endDate", "1 Year from the date of this agreement");
        context.setVariable("serviceFee", "A fee equal to 8% of the invoice value plus GST for a service provided by the\r\n"
                + //
                "Supplier to a customer under a booking processed using the Application will be\r\n"
                + //
                "payable. This fee will be capped and not exceed $50 plus GST per individual job\r\n"
                + //
                "processed.");
        context.setVariable("RtbOwnerSign", "https://i.pinimg.com/474x/67/0c/e5/670ce5fcf05472b52600e21c84c94884.jpg");

        context.setVariable("OwnerOrDirectorSign", "https://upload.wikimedia.org/wikipedia/commons/4/4e/MS_Dhoni_signature.png");

        context.setVariable("DirectorOrSecretarySign", "https://upload.wikimedia.org/wikipedia/commons/4/4e/MS_Dhoni_signature.png");

        byte[] pdf = pdfGeneratorService.generatePdf("contract", context);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "contract.pdf");

        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}
