package com.synapse.ai.volvo.Controller;

import com.synapse.ai.volvo.Service.R2StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final R2StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        String key = storageService.upload(file,"zip-files");

        return ResponseEntity.ok(key);
    }
}
