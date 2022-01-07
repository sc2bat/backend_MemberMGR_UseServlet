package com.ezen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.controller.action.Action;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 같이 전달된 command 파라미터를 getParameter 메소드로 받습니다
		String command = request.getParameter("command");
		// command 에 전달된 값에 따라 어떤 작업을 할지가 결정이 되어 실행될 예정입니다
		
		// 각 command 별 실행할 작업들은 해당 실행코드가 들어있는 클래스 내부 그 중에서도 execute() 메소드 안에 있습니다
		// Model2 방식은 각 기능별로 클래스가 제작되어 실행되기를 기다리고, command 값에 따라 선택되어 실행되는 형식입니다
		
		Action ac = null;
		/*
		if(command.equals("loginForm")) {
//			LoginFormAction lfa = new LoginFormAction();
//			lfa.execute(request, response);
			ac = new LoginFormAction();
		}else if(command.equals("login")){
			// 로그인 동작이 들어있는 클래스의 new 인스턴스를 만들고, execute 메소드 호출
//			LoginAction la = new LoginAction();
//			la.execute(request, response);
			ac = new LoginAction();
		}else if(command.equals("joinForm")) {
//			JoinFormAction jfa = new JoinFormAction();
//			jfa.execute(request, response);
			ac = new JoinFormAction();
		}*/
		
		// 각 기능을 탑재하고 있는 new 인스턴스의 생성과 execute() 의 실행은 클래스들 상속(implements) 
		// 받은 부모 인터페이스(Action) 의 레퍼런스 변수에 저장하고, 레퍼런스 변수명.execute 로 실행합니다
		
		// 각 클래스에 있는 execute 메소드는 Action 인터페이스에 존재하는 추상메소드입니다
		// 각 클래스가 Action 인터페이스를 상속(implements)하여, execute 메소드가 오버라이딩되면,
		// Action 인터페이스의 레퍼런스 변수로 자식 클래스의 execute 메소드를 호출하여 사용합니다.
		
//		ActionFactory af = new ActionFactory();
		ActionFactory af = ActionFactory.getInstance();
		
		
		ac = af.getAction(command); 
		// command 를 전달하면 메소드에서 위의 조립동작을 한 후 결과 객체를 리턴해줍니다
		if(ac == null) {
			System.out.println("ac 의 값이 null 입니다 command 값 확인하기");
			System.out.printf("command : " + command);
		}
		ac.execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}