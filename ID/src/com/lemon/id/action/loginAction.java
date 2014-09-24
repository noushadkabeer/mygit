package com.lemon.id.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lemon.id.service.AuthenticationService;
import com.lemon.id.service.impl.AuthenticationServiceImpl;

/**
 * Servlet implementation class loginAction
 */
@WebServlet("/loginAction")
public class loginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		System.out.println("Authenticating.. ");
//		AuthenticationService authService = new AuthenticationServiceImpl();
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		
//		if(username!=null || password!=null)
//			authService.authenticate(username, password);
//		else
//			response.getWriter().print("Username or password doesnt match. Please rety");
//		
//		response.getWriter().print("User Authenticated..");
//		response.sendRedirect("welcome.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authenticated = "";
		AuthenticationService authService = new AuthenticationServiceImpl();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username!=null || password!=null){
			System.out.println("Authenticating.. "+username+" : "+password);
			 authenticated = authService.authenticate(username, password);
			 request.getSession().setAttribute("authenticated", "authenticated");
			 request.getSession().setAttribute("ticket", authenticated);
			}
		else{
			response.getWriter().print("Username or password doesnt match. Please rety");
			response.sendRedirect("index.jsp");
		}
		response.getWriter().print("User Authenticated..");
		response.sendRedirect("welcome.jsp");
	}

}
