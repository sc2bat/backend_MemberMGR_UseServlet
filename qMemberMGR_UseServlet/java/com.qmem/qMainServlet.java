package com.ezen.qmem;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.MDao;
import com.ezen.dto.MDto;

@WebServlet("/qmain.do")
public class qMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public qMainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MDao mdao = MDao.getInstance();
		ArrayList<MDto> qlist = mdao.selectQM();
		request.setAttribute("qmemberList", qlist);
		RequestDispatcher dp = request.getRequestDispatcher("qmain.jsp");
		dp.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
