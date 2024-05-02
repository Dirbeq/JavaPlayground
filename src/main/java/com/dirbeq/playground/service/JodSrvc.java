package com.dirbeq.playground.service;

import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.office.OfficeException;
import org.jodconverter.local.LocalConverter;
import org.jodconverter.local.office.LocalOfficeManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class JodSrvc {
    private static final String TMP_FILES_DIRECTORY = "/tmp/files/";

    private static File convertToFile(MultipartFile multipartFile) {
        File file = new File(TMP_FILES_DIRECTORY + multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return file;
    }

    public File convertToPDF(MultipartFile multipartFile) {
        try {
            String fileName = TMP_FILES_DIRECTORY + multipartFile.getOriginalFilename() + ".pdf";

            LocalOfficeManager officeManager = LocalOfficeManager.builder().portNumbers(8100).build();
            try {
                officeManager.start();

                File inputFile = new File(convertToFile(multipartFile).getAbsolutePath());
                File outputFile = new File(fileName);

                DocumentConverter converter = LocalConverter.builder().officeManager(officeManager).build();
                converter.convert(inputFile).to(outputFile).execute();

                return outputFile;
            } catch (OfficeException e) {
                e.printStackTrace();
            } finally {
                try {
                    officeManager.stop();
                } catch (OfficeException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}