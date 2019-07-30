package com.example.patserfelices.images;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class FileController {

    private FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @GetMapping("/files")
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

//    @GetMapping("/files/{id}")
//    public Optional<File> getImageById(@PathVariable Long id) {
//        return fileRepository.findById(id);
//    }

    @PostMapping(value = "/files", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<File> uploadFile(@RequestParam MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File processedFile = null;
        try {
            processedFile = new File(fileName, file.getContentType(), file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        File storedFile = fileRepository.save(processedFile);
        storedFile.setData(null);

        return ResponseEntity.ok()
                .body(storedFile);
    }

    @GetMapping(value = "/images/{id}")
    public @ResponseBody
    byte[] getImage(@PathVariable Long id) throws IOException {
//        InputStream in = getClass()
//                .getResourceAsStream("/com/baeldung/produceimage/image.jpg");
//        return IOUtils.toByteArray(in);
        Optional<File> optional = fileRepository.findById(id);
        return optional.map(File::getData).orElse(null);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        // Load file from database
        File file = fileRepository.findById(id).orElse(null);
        if (file == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @DeleteMapping("/files/{id}")
    public void deleteFile(@PathVariable Long id) {
        fileRepository.deleteById(id);
    }


}
