package com.dirbeq.playground.controller;

import com.dirbeq.playground.service.JodSrvc;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class JodCtrl {
    private final JodSrvc jodSrvc;

    public JodCtrl(JodSrvc jodSrvc) {
        this.jodSrvc = jodSrvc;
    }

    @PostMapping(value = "/api/testwordtopdfconvert", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> testwordtopdfconvert(
            @RequestPart MultipartFile file
    ) {
        long startTime = System.currentTimeMillis();
        jodSrvc.convertToPDF(file);
        return ResponseEntity.ok("Time taken: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
