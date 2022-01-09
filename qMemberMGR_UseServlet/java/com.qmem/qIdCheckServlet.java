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

@WebServlet("/qidcheck.do")
public class qIdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public qIdCheckServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		MDao mdao = MDao.getInstance();
		MDto mdto = mdao.getMember(userid);
		
		if(mdto == null) {
			request.setAttribute("result", 2);
		}else {
//			사용중 1
			request.setAttribute("result", 1);
		}
		request.setAttribute("userid", userid);
		
		RequestDispatcher dp = request.getRequestDispatcher("qmem/qidcheck.jsp");
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
