package ua.AvadaMedia.admin.ModelDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
//@Scope("prototype")
public class ObjectModelDAOHibernate implements IDelegateModelDAOHibernate {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Object... objects) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (Object o : objects)
            session.save(o);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(Object o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Session getSession() {
        Session session = sessionFactory.openSession();
        return session;
    }


}
