package com.ezen.qmem;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.dao.MDao;
import com.ezen.dto.MDto;


@WebServlet("/qLogin.do")
public class qLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public qLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "qmem/qloginForm.jsp";
		HttpSession session = request.getSession();
		
		if(session.getAttribute("qloginUser") != null) {
			url = "qmain.do";
		}
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		String url = "qmem/qloginForm.jsp";
		
		MDao mdao = MDao.getInstance();
		MDto mdto = mdao.getMember(id);
		
		if(mdto ==null) {
			request.setAttribute("message", "none id");
		}else if(mdto.getPwd()==null) {
			request.setAttribute("message", "암호오류 관리자문의");
		}else if( !mdto.getPwd().equals(pwd)) {
			request.setAttribute("message", "not match password");
		}else if(mdto.getPwd().equals(pwd)) {
			url = "qmain.do";
			HttpSession session = request.getSession();
			session.setAttribute("qloginUser", mdto);
		}else {
			request.setAttribute("message", "아무튼 로그인 안됨");
		}
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

}
