package lab.andersen.crudapp.utils;

import org.hibernate.Query;
import org.hibernate.Session;

public class SqlUtil {
    public static long findIdForCreateOperation(Session session, String findQuery) {
        long createdId;
        session.beginTransaction();
        Query query = session.createQuery(findQuery);
        createdId = (long) query.list().get(0);////
        session.getTransaction().commit();
        return createdId;
    }


}
