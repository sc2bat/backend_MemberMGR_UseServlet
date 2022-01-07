package com.ezen.board.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.board.dao.MemberDao;
import com.ezen.board.dto.MemberDto;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = mdao.getMember(userid);
		
		String url = "member/loginForm.jsp";
		
		if(mdto == null) {request.setAttribute("message", "아이디가 없습니다");
		}else if(mdto.getPwd() == null) {request.setAttribute("message", "비밀번호가 없습니다 관리자 문의");
		}else if(!mdto.getPwd().equals(pwd)) {request.setAttribute("message", "비밀번호가 일치하지 않습니다");
		}else if(mdto.getPwd().equals(pwd)) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mdto);
//			url = "main.jsp";
			url = "board.do?command=main";
		}else {request.setAttribute("message", "아무튼 로그인 실패");
		}
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
		
	}
}
