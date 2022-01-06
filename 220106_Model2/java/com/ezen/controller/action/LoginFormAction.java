package com.ezen.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFormAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/loginForm.jsp";
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null) {
//			url = "main.jsp";
			url = "member.do?command=main";
		}
		
//		RequestDispatcher dp = request.getRequestDispatcher("member/loginForm.jsp");
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}
	/*
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dp = request.getRequestDispatcher("member/loginForm.jsp");
		dp.forward(request, response);
	}*/
}
