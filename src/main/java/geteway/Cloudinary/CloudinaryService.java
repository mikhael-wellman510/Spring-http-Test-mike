package geteway.Cloudinary;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public String uploadFile(MultipartFile file)throws IOException {
        Map data = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = data.get("url").toString();
        log.info("Hasil data : {} " , data.get("url"));
        log.info("Hasil tes : {} " , data );

        return url;
    };

    public String updatedFile(MultipartFile file , String publicId)throws IOException{
        Map<String , Object> options = ObjectUtils.asMap(
                "public_id" , publicId,
                "overwrite" , true,
                "resource_type" , "auto"
        );

        Map update =cloudinary.uploader().upload(file.getBytes(), options);
        log.info("hasil updated : {} " , update);
        return update.get("url").toString()
                ;

    }

    public String deletedFile(String publicId)throws IOException{


        Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

        log.info("hasil deleted : {} " , result);
        return "";
    }
}
