package int221.inegrated.controller;

import int221.inegrated.file.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
public class PictureRestController {

    final StorageService storageService;

    @Autowired
    public PictureRestController(StorageService storageService) {
        this.storageService = storageService;
    }


    @PostMapping("/upload")
    public void uploadFile(@RequestParam("images") MultipartFile file) {
        storageService.store(file);
        System.out.print("You ca upload file" + file);
    }

    @GetMapping(value = "/file/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public Resource serveFile(@PathVariable String filename) {
        return storageService.loadAsResource(filename);
    }

    @DeleteMapping(value = "/deletefile/{filename}", produces = MediaType.IMAGE_PNG_VALUE)
    public void deleteFile(@RequestParam("images") String filename) throws IOException {
        storageService.delete(filename);
    }
}
