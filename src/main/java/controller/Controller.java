package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.ActionForward;
import service.member.MemberInsert;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
protected void process(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	
	String requestURI = request.getRequestURI();
	String contextPath = request.getContextPath();
	String command = requestURI.substring(contextPath.length());
	
	Action  action = null;
	ActionForward forward = null;
	
	// 회원 가입
			if(command.equals("/MemberInsert.do")) {
				try {
					action = new MemberInsert();
					forward = action.execute(request, response);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			
			
			// 포워딩 처리
			if(forward != null) {
				if(forward.isRedirect()) {  // redirect 방식으로 포워딩
					response.sendRedirect(forward.getPath());
				}else {						// dispatcher 방식으로 포워딩
					RequestDispatcher dispatcher =
						request.getRequestDispatcher(forward.getPath());
					dispatcher.forward(request, response);
				}
			}	
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("get");
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		process(request, response);
		
	}
	
	

}
