package ua.AvadaMedia.adminREST.Service;

import org.springframework.stereotype.Service;
import ua.AvadaMedia.adminREST.ModelDAO.IDelegateModelDAOHibernate;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UniversalHQLQueriesService<T, K> {

    public Map<Long, T> getContentFromDataBaseWithPagination(
            IDelegateModelDAOHibernate<?> hibernate, String entityName, int id, int max, Function<T, K> keyMapper
    ) {
        TempMap tempMap = new TempMap();
        hibernate.workWithSession(session -> {
            tempMap.map = (Map<Long, T>) session.createQuery(
                    "FROM " + entityName +
                            " AS Entity WHERE Entity.id > " + id + ""
            ).setMaxResults(max).list().stream().collect(Collectors.toMap(keyMapper, Function.identity()));
        });
        return tempMap.map;
    }

    public long getFullSizeTable(IDelegateModelDAOHibernate<?> hibernate, String entityName) {
        TempSize tempSize = new TempSize();
        hibernate.workWithSession(session -> {
            tempSize.size = (long) session.createQuery("SELECT count(*) FROM " + entityName)
                    .list().get(0);
        });
        return tempSize.size;
    }

    private class TempMap {
        public Map<Long, T> map = null;
    }

    private class TempSize {
        public long size = 0;
    }

}
