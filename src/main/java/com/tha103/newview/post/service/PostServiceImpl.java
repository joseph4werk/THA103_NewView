package com.tha103.newview.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.tha103.newview.post.model.PostDAO;
import com.tha103.newview.post.model.PostDAOImpl;
import com.tha103.newview.post.model.PostVO;
import com.tha103.util.HibernateUtil;

public class PostServiceImpl implements PostService {

	private PostDAO postdao;
	private static final int PAGE_SIZE = 10;

	public PostServiceImpl() {
		postdao = new PostDAOImpl();
	}

	@Override
	public int addPost(PostVO postVO) {

		return postdao.insert(postVO);
	}

	@Override
	public int updatePost(PostVO postVO) {
		return postdao.update(postVO);

	}

	@Override
	public int deletePost(int postID) {
		return postdao.delete(postID);
	}

	@Override
	public PostVO getPostByPK(int postID) {
		return postdao.findByPrimaryKey(postID);
	}

	@Override
	public List<PostVO> getAll() {
		return postdao.getAll();
	}

//	@Override
//	public List<PostVO> getPostsByCompositeQuery(Map<String, String[]> map, int currentPage, String orderBy) {
//	    // 確保 orderBy 參數在方法中可用
//	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//	    Map<String, String> query = new HashMap<>();
//	    // Map.Entry即代表一組key-value
//	    Set<Map.Entry<String, String[]>> entry = map.entrySet();
//
//	    for (Map.Entry<String, String[]> row : entry) {
//	        String key = row.getKey();
//	        // 請求參數包含Action，做去除動作
//	        if ("action".equals(key)) {
//	            continue;
//	        }
//	        // 若是value為空表示沒有查詢條件
//	        String value = row.getValue()[0];
//	        if (value == null || value.isEmpty()) {
//	            continue;
//	        }
//	        query.put(key, value);
//	    }
//
//	    try {
//	        session.beginTransaction();
//	        // 計算分頁起始位置
//	        int start = (currentPage - 1) * PAGE_SIZE;
//	        List<PostVO> list = postdao.getByCompositeQuery(query, orderBy, start, PAGE_SIZE);
//	        session.getTransaction().commit();
//	        return list;
//	    } catch (Exception e) {
//	        session.getTransaction().rollback();
//	        e.printStackTrace();
//	        return null;
//	    }
//	}

//	@Override
//	public int getTotalPostCount(Map<String, String[]> map) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Map<String, String> query = new HashMap<>();
//		// Map.Entry即代表一组key-value
//		Set<Map.Entry<String, String[]>> entry = map.entrySet();
//
//		for (Map.Entry<String, String[]> row : entry) {
//			String key = row.getKey();
//			// 請求參數包含Action，做去除動作
//			if ("action".equals(key)) {
//				continue;
//			}
//			// 若是value為空表示沒有查詢條件
//			String value = row.getValue()[0];
//			if (value == null || value.isEmpty()) {
//				continue;
//			}
//			query.put(key, value);
//		}
//
//		try {
//			session.beginTransaction();
//			int total = postdao.getTotalCountByCompositeQuery(query);
//			session.getTransaction().commit();
//			return total;
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//			return 0;
//		}
//	}

}
