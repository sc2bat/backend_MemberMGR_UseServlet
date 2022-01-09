package com.ezen.qmem;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.MDao;

@WebServlet("/qdrawal.do")
public class qWithDrawal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public qWithDrawal() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		MDao mdao = MDao.getInstance();
		mdao.qdeleteM(userid);
		
		RequestDispatcher dp = request.getRequestDispatcher("qmem/qloginForm.jsp");
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
