import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dsvodlq5d",
                "api_key", "325257939927122",
                "api_secret", "hFaOAX00E9c-TzAH66vU80htg1w-A",
                "secure", true));
    }
}
