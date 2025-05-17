package geteway.Redis.Controller;

import geteway.Redis.Dto.ProfileDto;
import geteway.Redis.Service.ProfileIndexingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProfileIndexingController {

    private final ProfileIndexingService profileIndexingService;

    @PostMapping("/sedderProfile")
    public void saveProfile(){
        profileIndexingService.saveProfile();
    }

    @GetMapping("/findProfileCache/{id}")
    public ResponseEntity<?>findpProfCache(@PathVariable Long id){

        var data = profileIndexingService.findDataById(id);

        return ResponseEntity.ok(data);
    }

    @GetMapping("/findProfile/{name}")
    public ResponseEntity<?>findProfileCtr(@PathVariable String name){
        var res = profileIndexingService.findByName(name);
        log.info("Testing controller");
        return ResponseEntity.ok(res);
    }

    @PostMapping("/sendDatas")
    public ResponseEntity<?>sendDatas(@RequestBody ProfileDto profileDto){
        var res = profileIndexingService.saveData(profileDto);

        return ResponseEntity.ok(res);
    }
}
