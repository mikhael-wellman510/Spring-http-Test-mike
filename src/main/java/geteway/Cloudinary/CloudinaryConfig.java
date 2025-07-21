package geteway.Cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dwmszbp9e",
                "api_key", "259521317435399",
                "api_secret", "XAvRbBMVgLYvtnAS-pumgclsCDo",
                "secure",true
        ));
    }
}
