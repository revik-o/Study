package ua.AvadaMedia.admin.ModelDAO;

import org.hibernate.Session;

import java.util.List;

public interface IDelegateModelDAOHibernate {

    void add(Object... objects);
    void add(Object o);
    Session getSession();

}
