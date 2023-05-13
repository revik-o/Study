package ua.AvadaMedia.admin;

import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.AvadaMedia.admin.Model.Movie;
import ua.AvadaMedia.admin.ModelDAO.ObjectModelDAOHibernate;

import java.util.List;

@SpringBootTest
class AdminApplicationTests {

//	@Autowired
//	ObjectModelDAOHibernate<Movie> objectModelDAOHibernate;

	@Test
	void contextLoads() {
		Movie movie = new Movie();
		movie.setName("the gentleman");
		movie.setDescription("Nice movie");
//		Session session = objectModelDAOHibernate.getSessionAndBeginTransaction();
//		session.save(movie);
//		objectModelDAOHibernate.commitAndCloseSession();
//		List<Movie> list = objectModelDAOHibernate.getSessionAndBeginTransaction().createQuery("from Movie").list();
//		Assertions.assertEquals(movie.getName(), list.get(list.size() - 1).getName());
	}

}
