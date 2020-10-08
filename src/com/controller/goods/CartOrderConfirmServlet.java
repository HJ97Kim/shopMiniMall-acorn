package com.controller.goods;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.service.CartService;
import com.service.GoodsService;
import com.service.MemberService;

/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/CartOrderConfirmServlet")
public class CartOrderConfirmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");
		String nextPage = null;
		
		if(dto != null) {
			
			String num = request.getParameter("num"); // CartDTO
			
			CartService cService = new CartService();
			CartDTO cDTO = cService.cartbyNum(num); // Cart 정보 조회
			
			String userid = dto.getUserid();
			MemberService mService = new MemberService();
			MemberDTO mDTO = mService.mypage(userid);
			request.setAttribute("cDTO", cDTO);
			request.setAttribute("mDTO", mDTO);
			
			nextPage = "orderConfirm.jsp";
		} else {
			nextPage = "LoginUIServlet";
			session.setAttribute("mesg", "로그인이 필요한 작업입니다");
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);

//로그인 정보확인
//num이용 장바구니 정보 조회 =>service.cartByNum MapperId= cartbyNum
//userid 이용 사용자 정보 조회 =>service.mypage(userid)기존 구현된 함수 이용
//request에 장바구니 정보, 사용자 정보 set
//orderConfirm.jsp로 전송
//회원이 아닌 경우 처리 

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
