package com.ezen.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.dao.MemberDao;
import com.ezen.dto.MemberDto;

public class LoginAction implements Action {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher dp = request.getRequestDispatcher("member/loginForm.jsp");
//		dp.forward(request, response);
		
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = mdao.getMember(userid);
		
		String url = "member/loginForm.jsp";
		if(mdto == null) {
			request.setAttribute("message", "없는 아이디입니다.");
		}else if(mdto.getPwd() == null) {
			request.setAttribute("message", "비밀번호 오류입니다.");
		}else if( !mdto.getPwd().equals(pwd)) {
			request.setAttribute("message", "비밀번호가 다릅니다");
		}else if(mdto.getPwd().equals(pwd)) {
			url = "main.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mdto);
		}else {
			request.setAttribute("message", "아무튼 로그인 실패.");
		}
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}
}
