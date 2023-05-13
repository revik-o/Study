package ua.AvadaMedia.MainService.Configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class HibernateUtil {

    @Bean
    public SessionFactory getSessionFactory() {
        HashMap<String, String> hibernateSettings = new HashMap<>();
        hibernateSettings.put(Environment.URL, "");

    }

}