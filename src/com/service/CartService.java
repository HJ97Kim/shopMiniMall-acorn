package com.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.config.MySqlSessionFactory;
import com.dao.CartDAO;
import com.dto.CartDTO;
import com.dto.OrderDTO;

public class CartService {

	public int cartAdd(CartDTO dto) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.cartAdd(session, dto);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}

	public List<CartDTO> cartList(String userid) {
		SqlSession session = MySqlSessionFactory.getSession();
		List<CartDTO> list = null;
		try {
			CartDAO dao = new CartDAO();
			list = dao.cartList(session, userid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public int cartDel(int num) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.cartDel(session, num);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}

	public int cartUpdate(HashMap<String, Integer> map) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.cartUpdate(session, map);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}

	public int cartAllDel(List<String> list) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.cartAllDel(session, list);
			session.commit();
		} finally {
			session.close();
		}
		return n;
	}

	public CartDTO cartbyNum(String num) {
		SqlSession session = MySqlSessionFactory.getSession();
		CartDTO list = null;
		try {
			CartDAO dao = new CartDAO();
			list = dao.cartbyNum(session, num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public int orderDone(OrderDTO dto, String orderNum) {
		SqlSession session = MySqlSessionFactory.getSession();
		int n = 0;
		try {
			CartDAO dao = new CartDAO();
			n = dao.orderDone(session, dto); // order테이블에 추가
			n = dao.cartDel(session, Integer.parseInt(orderNum)); // cart테이블에서 삭제
			session.commit(); // 두작업 완료 후 커밋
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback(); //tx 처리 롤백
		} finally {
			session.close();
		}
		return n;
	}


}// end class
