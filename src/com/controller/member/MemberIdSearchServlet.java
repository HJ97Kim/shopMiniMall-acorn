package com.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDTO;
import com.service.MemberService;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/MemberIdSearchServlet")
public class MemberIdSearchServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username").trim();
		String phone1 = request.getParameter("phone1").trim();
		String phone2 = request.getParameter("phone2").trim();
		String phone3 = request.getParameter("phone3").trim();
		String email1 = request.getParameter("email1").trim();
		String email2 = request.getParameter("email2").trim();
		
		MemberDTO dto = new MemberDTO();
		dto.setUsername(username);
		dto.setPhone1(phone1);
		dto.setPhone2(phone2);
		dto.setPhone3(phone3);
		
		MemberService service = new MemberService();
		String userid = service.idSearch(dto);
		String nextPage = null;
		if(userid == null) {
			nextPage = "MemberIdSearchUIServlet";
			request.setAttribute("mesg", "이름 또는 핸드폰이 등록되지 않은 정보");
		} else {
			nextPage = "SendMailServlet";
			request.setAttribute("mailTo", email1+"@"+email2);
			request.setAttribute("userid", userid);
		}
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
		 //데이터파싱
		 
		 //데이터 파싱, servcie.idSearch(MemberDTO)
		 //사용자이름, 폰 1,2,3, 번호가 일치하는 사람의 id값 가져오기( Mapper id= idSearch 사용) 
		//가져온 id가 있을 경우 		
			// 이동페이지"SendMailServlet"로 필요한 data전송 
			//받을  사람의 email 주소를 mailTo ,  xxx@naver.com 으로 전송
			// userid, 사용자 id 전송

		//가져온 id가 없는 경우  idSeard.jsp로 화면 전환 메세지 전송 "전화번호 확인이 필요합니다."
		
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
