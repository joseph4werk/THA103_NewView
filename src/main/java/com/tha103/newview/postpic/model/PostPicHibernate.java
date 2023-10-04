package com.tha103.newview.postpic.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.Session;

import com.tha103.newview.post.model.PostVO;
import com.tha103.util.HibernateUtil;

public class PostPicHibernate implements PostPicDAO_interface {

	@Override
	public int add(PostPicVO postPicVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(postPicVO);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(PostPicVO postPicVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(postPicVO);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer postPicID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PostPicVO postcategory = session.get(PostPicVO.class, postPicID);
			if (postcategory != null) {
				session.delete(postcategory);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public PostPicVO findByPK(Integer postPicID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PostPicVO postcategory = session.get(PostPicVO.class, postPicID);
			session.getTransaction().commit();
			return postcategory;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<PostPicVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<PostPicVO> list = session.createQuery("from PostPicVO", PostPicVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;

	}

	public byte[] gifToByteArray(String gif) throws IOException {
		File gifFile = new File(gif);

		BufferedImage image = ImageIO.read(gifFile);

		if (image != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "gif", baos);
			return baos.toByteArray();
		} else {
			throw new IOException("failed");
		}
	}

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		PostPicDAO_interface dao = new PostPicHibernate();

		// Insert
		PostPicVO pic1 = new PostPicVO();
		PostVO post1 = new PostVO();
		pic1.setPost(post1);
		post1.setPostID(1);
		byte[] img = null;
		try {
			img = PostPicHibernate.getPictureByteArray("C:/THA103_WebApp/Concert.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		pic1.setPostPic(img);
		dao.add(pic1);

		// Update
//		PostPicVO pic2 = new PostPicVO();
//		PostVO post2 = new PostVO();
//		pic2.setPostPicID(9);
//		pic2.setPost(post2);
//		post2.setPostID(1);
//		byte[] img2 = null;
//		try {
//			img2 = getPictureByteArray("C:/THA103_WebApp/Concert.jpg");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		pic2.setPostPic(img2);
//		dao.update(pic2);

		// Delete
//		dao.delete(12);

		// FindPK//
		PostPicVO pic3 = dao.findByPK(1);
		System.out.println(pic3);

		// ListAll-1
		List<PostPicVO> list = dao.getAll();
		for (PostPicVO lists : list) {
			System.out.println(lists);
		}

		// ListAll-2
		List<PostPicVO> list2 = new PostPicHibernate().getAll();
		System.out.println(list2);
	}
}
