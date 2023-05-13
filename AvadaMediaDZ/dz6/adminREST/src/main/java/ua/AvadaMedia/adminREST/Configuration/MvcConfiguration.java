package ua.AvadaMedia.adminREST.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.AvadaMedia.adminREST.Service.UploadFileService;

import java.io.File;

@Configuration
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private UploadFileService uploadFileService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/files_for_db/**")
                .addResourceLocations("file:///" + new File(uploadFileService.getUploadPath()).getAbsolutePath() + "/");
    }
}
