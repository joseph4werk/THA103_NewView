package com.tha103.newview.act.model;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tha103.newview.actpic.model.ActPic;
import com.tha103.util.HibernateUtil;

public class ActDAOHibernateImpl implements ActDAO {

    private SessionFactory factory;

    public ActDAOHibernateImpl() {
    }

    public ActDAOHibernateImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void insert(ActVO act) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Integer id = (Integer) session.save(act);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void update(ActVO act) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(act);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Integer ActID) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            ActVO act = session.get(ActVO.class, ActID);
            if (act != null) {
                session.delete(act);
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public ActVO findByPrimaryKey(Integer actID) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        ActVO act = null;
        try {
            session.beginTransaction();
            act = session.get(ActVO.class, actID);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return act;
    }

    @Override
    public List<ActVO> getAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<ActVO> list = session.createQuery("from ActVO", ActVO.class).list();
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String json = gson.toJson(list);

            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

  

    @Override
    public List<ActVO> getActPics() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<ActVO> list = session.createQuery("select distinct a from ActVO a left join fetch a.actpics", ActVO.class).list();
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String json = gson.toJson(list);
            System.out.println(json);
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }


}
