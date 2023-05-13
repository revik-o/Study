package ua.avadamedia.dz4.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//@Configuration
public class JSPConfiguration {

//    @Bean
    public ViewResolver jspTemplateResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setPrefix("/");
        internalResourceViewResolver.setSuffix(".jsp");
        internalResourceViewResolver.setContentType("text/html");
        internalResourceViewResolver.setViewNames("jsp/*");
        internalResourceViewResolver.setOrder(0);
        return internalResourceViewResolver;
    }

}
