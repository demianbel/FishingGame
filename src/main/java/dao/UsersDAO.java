package dao;

import dataSets.UserDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by Демьян on 11.01.2017.
 */
public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UserDataSet getUser(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return ((UserDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult());
    }

    public long insertUser(UserDataSet user) throws HibernateException {
        return (Long) session.save(user);
    }

    public void saveUser(UserDataSet user) {
        session.update(user);
    }
}

