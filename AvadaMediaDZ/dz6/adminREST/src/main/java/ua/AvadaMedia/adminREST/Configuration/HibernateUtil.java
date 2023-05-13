package ua.AvadaMedia.adminREST.Configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class HibernateUtil {

    @Bean
    public SessionFactory getSessionFactory() {
        // Create MetadataSources
        MetadataSources sources = new MetadataSources(new StandardServiceRegistryBuilder().configure().build());

        // Create Metadata -> connect to MySQL
        Metadata metadata = sources.getMetadataBuilder().build();

        // Create SessionFactory
        return metadata.getSessionFactoryBuilder().build();
    }

}