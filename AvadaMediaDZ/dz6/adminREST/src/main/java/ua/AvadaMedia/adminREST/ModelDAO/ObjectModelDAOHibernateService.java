package ua.AvadaMedia.adminREST.ModelDAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.AvadaMedia.adminREST.DelegateMethod.IBooleanDelegateMethodForSession;
import ua.AvadaMedia.adminREST.DelegateMethod.IDelegateMethodForSession;

@Service
//@Scope("prototype")
public class ObjectModelDAOHibernateService<T> implements IDelegateModelDAOHibernate<T> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(T... tArgs) {
        workWithSession(session -> { add(session, tArgs); });
    }

    public void add(Session session, T... tArgs) {
        for (T t: tArgs) session.save(t);
    }

    @Override
    public void workWithSession(IDelegateMethodForSession delegateMethod) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        delegateMethod.method(session);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean workWithSession(IBooleanDelegateMethodForSession delegateMethod) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        boolean result = delegateMethod.method(session);
        session.getTransaction().commit();
        session.close();
        return result;
    }

}