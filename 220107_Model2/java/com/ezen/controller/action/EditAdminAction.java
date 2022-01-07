package com.ezen.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.MemberDao;
import com.ezen.dto.MemberDto;

public class EditAdminAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = mdao.getMember(userid);
		
		if(mdto.getAdmin() == 1) {
//			mdao.editAdmin(mdto.getUserid(), 0);
			mdao.editAdmin(userid, 0);
		}else {
			mdao.editAdmin(userid, 1);
		}
		
		RequestDispatcher dp = request.getRequestDispatcher("member.do?command=main");
		dp.forward(request, response);
	}

}
