package com.tha103.newview.act.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tha103.newview.act.controller.ActWithPicsDTO;
import com.tha103.newview.actcategory.model.ActCategory;
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

          
            ActVO originalAct = session.get(ActVO.class, act.getActID());

            if (originalAct != null) {
                if (act.getActName() != null && !act.getActName().isEmpty() &&
                    !act.getActName().equals(originalAct.getActName())) {
                    originalAct.setActName(act.getActName());
                }
                
                if (act.getActPrice() != null && !act.getActPrice().equals(originalAct.getActPrice())) {
                    originalAct.setActPrice(act.getActPrice());
                }
                
                if (act.getActTime() != null && !act.getActTime().equals(originalAct.getActTime())) {
                    originalAct.setActTime(act.getActTime());
                }
                
                if (act.getActScope() != null && !act.getActScope().equals(originalAct.getActScope())) {
                    originalAct.setActScope(act.getActScope());
                }
                
                if (act.getActIntroduce() != null && !act.getActIntroduce().isEmpty() &&
                    !act.getActIntroduce().equals(originalAct.getActIntroduce())) {
                    originalAct.setActIntroduce(act.getActIntroduce());
                }
                
                if (act.getActContent() != null && !act.getActContent().isEmpty() &&
                    !act.getActContent().equals(originalAct.getActContent())) {
                    originalAct.setActContent(act.getActContent());
                }
                
                if (act.getTime() != null && !act.getTime().equals(originalAct.getTime())) {
                    originalAct.setTime(act.getTime());
                }
                
                if (act.getActDate() != null && !act.getActDate().equals(originalAct.getActDate())) {
                    originalAct.setActDate(act.getActDate());
                }
                
                if (act.getApprovalCondition() != null && !act.getApprovalCondition().equals(originalAct.getApprovalCondition())) {
                    originalAct.setApprovalCondition(act.getApprovalCondition());
                }
                
                if (act.getCityAddress() != null && !act.getCityAddress().isEmpty() &&
                    !act.getCityAddress().equals(originalAct.getCityAddress())) {
                    originalAct.setCityAddress(act.getCityAddress());
                }
                if (act.getCityAddressID() != null && !act.getCityAddressID().isEmpty() &&
                        !act.getCityAddressID().equals(originalAct.getCityAddressID())) {
                        originalAct.setCityAddressID(act.getCityAddressID());
                    }
                
                if (act.getActCategory() != null && !act.getActCategory().equals(originalAct.getActCategory())) {
                    originalAct.setActCategory(act.getActCategory());
                }

                session.update(originalAct);
            }

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
    public List<ActVO> getAllWithAssociations() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<ActVO> list = session.createQuery("from ActVO act " +
                    "left join fetch act.actCategory " +
                    "left join fetch act.actpics " +
                    "left join fetch act.city", ActVO.class)
                    .list();

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

    @Override
    public List<ActCategory> getAllCategories() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<ActCategory> categories = session.createQuery("FROM ActCategory", ActCategory.class).list();
            session.getTransaction().commit();
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }


   



   

}