package geteway.controller;


import geteway.service.BiodataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BiodataController {

    private final BiodataService biodataService;

    @GetMapping("/bio")
    public ResponseEntity<?>findBio(@RequestParam(value = "page", defaultValue = "0")Integer page , @RequestParam(value = "size" , defaultValue = "5")Integer size , @RequestParam(value = "title" , defaultValue = "")String title){

        var res = biodataService.findBiodataByName(page,size,title);

        return ResponseEntity.ok(res);
    }
}
