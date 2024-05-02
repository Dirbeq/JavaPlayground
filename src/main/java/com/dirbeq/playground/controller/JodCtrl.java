package com.dirbeq.playground.controller;

import com.dirbeq.playground.service.JodSrvc;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class JodCtrl {
    private final JodSrvc jodSrvc;

    public JodCtrl(JodSrvc jodSrvc) {
        this.jodSrvc = jodSrvc;
    }

    @PostMapping(value = "/testwordtopdfconvert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> testwordtopdfconvert(
            @RequestPart MultipartFile file
    ) throws IOException {
        File pdfFile = jodSrvc.convertToPDF(file); // Assuming convertToPDF now returns the converted File
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + pdfFile.getName());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfFile.length())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello World");
    }
}
