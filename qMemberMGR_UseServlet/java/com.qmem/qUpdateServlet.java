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

@WebServlet("/qupdate.do")
public class qUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public qUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "qmem/qupdateForm.jsp";
		HttpSession session = request.getSession();
		if(session.getAttribute("qloginUser") == null) {
			url = "qmem/qloginForm.jsp";
		}
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MDto mdto = new MDto();
		MDao mdao = MDao.getInstance();
		mdto.setName(request.getParameter("name"));
		mdto.setUserid(request.getParameter("id"));
		mdto.setPwd(request.getParameter("pwd"));
		mdto.setEmail(request.getParameter("email"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setAdmin(Integer.parseInt(request.getParameter("admin")));
		
		int result = mdao.qupdateM(mdto);
		
		if(result == 1) {
			request.setAttribute("message", "update complete");
			HttpSession session = request.getSession();
			session.setAttribute("qloginUser", mdto);
		}else {
			request.setAttribute("message", "update failed");
		}
		
		RequestDispatcher dp = request.getRequestDispatcher("qmain.do");
		dp.forward(request, response);
	}

}
