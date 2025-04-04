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
        context.setVariable("name", "John Doe");
        context.setVariable("amount", "Australia");
        context.setVariable("date", "2024-04-04");
        context.setVariable("businessname", "PIXID AI");
         context.setVariable("email", "pixid.ai@mail.com");

        byte[] pdf = pdfGeneratorService.generatePdf("contract", context);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "contract.pdf");

        return ResponseEntity.ok().headers(headers).body(pdf);
    }
}
