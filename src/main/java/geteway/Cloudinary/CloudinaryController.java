package geteway.Cloudinary;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CloudinaryController {

    private final CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<?>uploadImage(@RequestParam(name = "file")MultipartFile file){
        try {
            Object data = cloudinaryService.uploadFile(file);

            log.info("Info data : {} " , data);

            return ResponseEntity.ok(data);
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/updated-foto")
    public ResponseEntity<?>updatedImage(@RequestParam(name = "file")MultipartFile file , @RequestParam(name = "publicId") String publicId){
        try {
            Object data = cloudinaryService.updatedFile(file , publicId);

            return ResponseEntity.ok(data);
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<?>deletedImage(@PathVariable String id) throws IOException {
        var data = cloudinaryService.deletedFile(id);

        return ResponseEntity.ok(data);
    }
}
