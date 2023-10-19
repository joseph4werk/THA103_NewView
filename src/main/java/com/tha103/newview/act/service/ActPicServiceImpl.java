package com.tha103.newview.act.service;

import org.hibernate.Session;

import com.tha103.newview.act.model.ActDAO;
import com.tha103.newview.actpic.model.ActPic;
import com.tha103.newview.actpic.model.ActPicDAO;
import com.tha103.util.HibernateUtil;

public class ActPicServiceImpl implements ActPicService{
	private ActPicDAO actPicDAO;
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	
	public ActPicServiceImpl() {
	    
	}
	public ActPicServiceImpl(ActPicDAO actPicDAO) {
	    this.actPicDAO = actPicDAO;
	}
	
//	public void deleteActPic(String toDelete) {
//        if (toDelete != null && !toDelete.isEmpty()) {
//            try {
//                int deleteId = Integer.parseInt(toDelete);
//                ActPic actPicToDelete = actPicDAO.findByPrimaryKey(deleteId);
//
//                if (actPicToDelete != null) {
//                    actPicDAO.delete(actPicToDelete.getActPicID());
//                } else {
//                   
//                }
//
//            } catch (NumberFormatException nfe) {
//                nfe.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
	public void deleteActPic(String toDelete) {
	    if (toDelete != null && !toDelete.isEmpty()) {
	        String[] ids = toDelete.split(","); // 分割字符串获取所有的ID

	        for(String idStr : ids) {
	            try {
	                int deleteId = Integer.parseInt(idStr);  // 将每个ID字符串转为整数
	                ActPic actPicToDelete = actPicDAO.findByPrimaryKey(deleteId);

	                if (actPicToDelete != null) {
	                    actPicDAO.delete(actPicToDelete.getActPicID());
	                } else {
	                    // 可能的错误处理，例如记录该ID没有找到对应的图片
	                }

	            } catch (NumberFormatException nfe) {
	                nfe.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}


}
