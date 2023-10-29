package com.tha103.newview.orders.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.tha103.newview.publisher.model.PublisherVO;
import com.tha103.util.HibernateUtil;

public class OrdersDAOImpl implements OrdersDAO {

	private static Timestamp now = new Timestamp(System.currentTimeMillis());

	@Override
	public int insert(OrdersVO ordersVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(ordersVO);
//				Integer id = (Integer) session.save(ordersVO);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return 1;

	}

	@Override
	public int update(OrdersVO ordersVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(ordersVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;

	}

	@Override
	public int delete(Integer orderID) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			OrdersVO ordersVO = session.get(OrdersVO.class, orderID);
			if (ordersVO != null) {
				session.delete(ordersVO);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;

	}

	@Override
	public OrdersVO findByPrimaryKey(Integer orderID) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			OrdersVO ordersHibernateVO = session.get(OrdersVO.class, orderID);
			session.getTransaction().commit();
			return ordersHibernateVO;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;

	}

	@Override
	public List<OrdersVO> getAll() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<OrdersVO> list = session.createQuery("from OrdersVO", OrdersVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;

	}
	
	
	
	//for Publisher Backstage get Order List by Mandy
	public List<OrdersVO> getOrdersByPubID(Integer pubID){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String hql = "FROM OrdersVO AS o WHERE o.publisherVO.pubID = :pubID";
			Query<OrdersVO> query = session.createQuery(hql,OrdersVO.class);
			query.setParameter("pubID",pubID);
			return query.getResultList();
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	

	

	@Override
	public int deleteOrdersbyUserIDandPubID(Integer userID, Integer pubID) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    try {
	        session.beginTransaction();

	        // 透過查詢來獲取特定的 OrdersVO
	        Query query = session.createQuery("from OrdersVO where publisherVO.pubID = :pubID and userVO.userID = :userID");
	        query.setParameter("pubID", pubID);
	        query.setParameter("userID", userID);
	        List<OrdersVO> orders = query.list();

	        for (OrdersVO order : orders) {
	            session.delete(order); // 刪除每個符合條件的訂單
	        }

	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	        return -1; // 返回錯誤碼
	    }
	    return 1; // 成功刪除
	}

	@Override
	public Integer getOrderBy(int userID, int pubID) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    try {
	        session.beginTransaction();

	        Query query = session.createQuery("SELECT orderVO.orderID FROM OrdersVO orderVO WHERE orderVO.userVO.userID = :userID AND orderVO.publisherVO.pubID = :pubID", Integer.class);
	        query.setParameter("userID", userID);
	        query.setParameter("pubID", pubID);

	        Integer orderID = (Integer) query.uniqueResult();
	        
	        session.getTransaction().commit();
	        return orderID != null ? orderID : 0; // Return 0 or handle null case according to your requirements
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    return 0;
	}




}
