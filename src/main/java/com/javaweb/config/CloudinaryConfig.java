package com.javaweb.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dk6kku8el",        
            "api_key", "462585933415641",             
            "api_secret", "w2ee_T1aj5cAVjuvJJXBMLukmpw",         
            "secure", true
        ));
    }
}
