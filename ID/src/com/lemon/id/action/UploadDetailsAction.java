package com.lemon.id.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lemon.id.exception.UnauthorizedException;

/**
 * Servlet implementation class UploadDetailsAction
 */
@WebServlet("/UploadDetailsAction")
public class UploadDetailsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadDetailsAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("authenticated").equals("authenticated")){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username!=null || password!=null){
			System.out.println("Uploading.. "+username+" : "+password);
			
			
			}
		else
			response.getWriter().print("Username or password doesnt match. Please rety");
		
		response.getWriter().print("User Authenticated..");
		response.sendRedirect("welcome.jsp");
		}else{
			response.sendRedirect("index.jsp");
		}
	}

}
