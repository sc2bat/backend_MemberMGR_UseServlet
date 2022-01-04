package com.ezen.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.dao.MemberDao;
import com.ezen.dto.MemberDto;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/loginForm.jsp";
		
		// 서블릿에서 세션은 아래와 같이 request 에서 getSession() 으로 전달받아야 사용이 가능합니다
		HttpSession session = request.getSession();
		// .jsp 파일에는 이미 request 와 response 와 session, application 등이
		// 이미 존재하기 때문에 session 을 바로 사용하는 것이 가능하지만,
		// 서블릿은 그렇지 않고, request 와 response 를 전달받아 사용하기 때문에
		// 전달된 request 를 통해 session 을 꺼내서 사용합니다
		
		if(session.getAttribute("loginUser") != null) {
			// loginUser 세션값이 null 이 아니라면,
			// url = "main.jsp"; // 누군가 로그인되어 있는 상태라면 포워딩 될 경로를 변경
			url = "main.do"; // 누군가 로그인되어 있는 상태라면 포워딩 될 경로를 변경
		}
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
//		RequestDispatcher dp = request.getRequestDispatcher("member/loginForm.jsp");
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전달된 아이디 암호 변수에 저장
		String id = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		// 전달된 id 로  member 테이블에서 회원을 검색하고, 검색결과에 따라 pwd 와 비교해서 정상 로그인 여부를 결정합니다
		// 정상 로그인 여부를 결정합니다
		
		// 로그인이 실패했을때를 대비해서 포워딩 할 경로를 먼저 설정합니다
		String url = "member/loginForm.jsp";
		// 정상 로그인시에 url 값이 main.jsp 로 변경됩니다
		
		// Dao 의 메소드를 호출하기 위해서 객체를 생성합니다
		MemberDao mdao = MemberDao.getInstance();
		// 아이디로 검색해서 해당 아이디의 멤버정보를 모두 dto 형태로 리턴받습니다
		MemberDto mdto = mdao.getMember(id);
		
		if(mdto == null) {
			request.setAttribute("message", "아이디 없음");
		}else if(mdto.getPwd() == null) {
			request.setAttribute("message", "암호 오류. 관리자에게 문의하세요");
		}else if( !mdto.getPwd().equals(pwd)) {
			request.setAttribute("message", "비밀번호가 틀림");
		}else if( mdto.getPwd().equals(pwd)) {
			// url = "main.jsp";
			url = "main.do";
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mdto); // 세션에 검색된 사용자를 저장합니다.
		}else {
			// 아무튼 로그인 실패 // 어쨌든 로그인 실패
			request.setAttribute("message", "무슨 이유에선지 로그인이 안됩니다.");
		}
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
	}

}
