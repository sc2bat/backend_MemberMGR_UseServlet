package com.ezen.qmem;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.MDao;
import com.ezen.dto.MDto;

@WebServlet("/qjoin.do")
public class qJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public qJoinServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dp = request.getRequestDispatcher("qmem/qjoinForm.jsp");
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MDto mdto = new MDto();
		MDao mdao = MDao.getInstance();
		
		String name = request.getParameter("name");
		mdto.setName(name);
		mdto.setUserid(request.getParameter("id"));
		mdto.setPwd(request.getParameter("pwd"));
		mdto.setEmail(request.getParameter("email"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setAdmin(Integer.parseInt(request.getParameter("admin")));
		
		int result = mdao.qinsertM(mdto);
		if(result == 1) {
			request.setAttribute("message", "sign up success");
		}else {
			request.setAttribute("message", "sign up failed");
		}
		
		RequestDispatcher dp = request.getRequestDispatcher("qmem/qloginForm.jsp");
		dp.forward(request, response);
	}

}
