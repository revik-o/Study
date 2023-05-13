package ua.AvadaMedia.adminREST.ModelDAO;

import ua.AvadaMedia.adminREST.DelegateMethod.IBooleanDelegateMethodForSession;
import ua.AvadaMedia.adminREST.DelegateMethod.IDelegateMethodForSession;

public interface IDelegateModelDAOHibernate<T> {

    void add(T... t);
//    void add(T t);
    void workWithSession(IDelegateMethodForSession delegateMethod);
    boolean workWithSession(IBooleanDelegateMethodForSession delegateMethod);

}