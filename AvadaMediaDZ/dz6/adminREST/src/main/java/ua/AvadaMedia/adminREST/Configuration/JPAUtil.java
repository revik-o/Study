/*
package ua.AvadaMedia.adminREST.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.AvadaMedia.adminREST.Model.Cinema;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//@Configuration
public class JPAUtil {

    @Bean
    public EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("KinoCMS");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Cinema cinema = new Cinema();
        cinema.setName("dadsaa");
        entityManager.persist(cinema);
        entityManager.createQuery("JPQL");
        entityManager.createNativeQuery("SQL");
        entityManager.getTransaction().commit();
        return entityManager;
    }

}*/
