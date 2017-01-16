package dao;

import dataSets.FishDataSet;
import dataSets.UserDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Демьян on 13.01.2017.
 */
public class FishesDAO {
    private Session session;

    public FishesDAO(Session session) {
        this.session = session;
    }

    public List<FishDataSet> getFishList() throws HibernateException {
        Criteria criteria = session.createCriteria(FishDataSet.class).addOrder(Order.asc("chance"));
        return criteria.list();
    }
}
