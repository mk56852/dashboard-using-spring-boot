package com.dash.dash.controller;


import com.dash.dash.Service.FileService;
import com.dash.dash.dto.FileDto;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController

@AllArgsConstructor
public class FileController {

    private FileService fileService ;


    @PostMapping("/add-file/{clientId}")
    public ResponseEntity uploadMultipleFiles(@RequestParam("file") MultipartFile file , @PathVariable(value = "clientId") Long id) {
            fileService.addFile(file , id);
            return ResponseEntity.ok("file added");
    }

    @GetMapping("/file/{id]")
    public ResponseEntity<FileDto> getFileById(@PathVariable Long id)
    {
        return ResponseEntity.ok(fileService.loadFile(id)) ;
    }
}
