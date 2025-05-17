package geteway.Redis.Service;

import geteway.Redis.Dto.ProfileDto;
import geteway.Redis.Profile;
import geteway.Redis.Repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileIndexingService {
    private final ProfileRepository profileRepository;
    private static String huruf = "abcdefghijklmnopqrstuvwxyz";
    private final CacheProfile cacheProfile;

    public ProfileDto findDataById(Long id){
        Profile findByCache = cacheProfile.getCachedProfile(id);


        // todo -> kalau ga ada , ambil dari db
        if (findByCache == null){
            log.info("Ambil dati database !!!");
            Profile p = profileRepository.findById(id).orElseThrow(()->new RuntimeException("not fpund"));

            // todo -> save data ke cache
            cacheProfile.cacheUser(p);

            return ProfileDto.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .nip(p.getNip())
                    .createdAt(p.getCreatedAt())
                    .build();
        }

        log.info("Dapat dari cache");

        return ProfileDto.builder()
                .id(findByCache.getId())
                .name(findByCache.getName())
                .nip(findByCache.getNip())
                .createdAt(findByCache.getCreatedAt())
                .build();
    }


    public ProfileDto saveData(ProfileDto profileDto){

        Profile p = profileRepository.save(Profile.builder()
                        .name(profileDto.getName())
                        .nip(profileDto.getNip())
                        .createdAt(LocalDateTime.now())
                .build());

        return ProfileDto.builder()
                .id(p.getId())
                .name(p.getName())
                .nip(p.getNip())
                .createdAt(p.getCreatedAt())
                .build();
    }

    public void saveProfile(){




        for (int j = 0 ; j < 5000000 ; j++){
            // membuat nama random
            Random random = new Random();

            StringBuilder name = new StringBuilder();
            StringBuilder nip = new StringBuilder();
            int min = 10000;
            int max = 99000;

            int min1 = 1500000;
            int max2 = 9900000;
            for (int i = 0 ; i < 6 ; i++){
                int angka = random.nextInt(huruf.length());
                name.append(huruf.charAt(angka));
            }
            int randomNum = random.nextInt((max-min) + 1)+ min;
            int randomNip = random.nextInt((max2-min1)+1) +min;
            name.append(randomNum);
            nip.append(randomNip);
            Profile p = Profile.builder()
                    .name(name.toString())
                    .nip(nip.toString())
                    .createdAt(LocalDateTime.now())
                    .build();

            profileRepository.save(p);

        }
    }

    public ProfileDto findByName(String name){
        try{
            Profile p = profileRepository.findByName(name).orElseThrow(()-> new RuntimeException("not found"));

            return ProfileDto.builder()
                    .id(p.getId())
                    .name(p.getName())
                    .nip(p.getNip())
                    .createdAt(p.getCreatedAt())
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
