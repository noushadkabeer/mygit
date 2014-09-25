package com.lemon.id.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lemon.id.service.AlfrescoContentService;
import com.lemon.id.service.impl.AlfrescoContentServiceImpl;;
/**
 * Servlet implementation class SubmitDetailsAction
 */
@WebServlet("/SubmitDetailsAction")
public class SubmitDetailsAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitDetailsAction() {
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
			 AlfrescoContentService contS = new AlfrescoContentServiceImpl();
			 String username = (String)request.getSession().getAttribute("username");
			 String address = request.getParameter("address");
			 String details = request.getParameter("details");
			 try{
				 contS.submitDetails(username, address, details);
			 }catch(Exception e){
				 e.printStackTrace();
				 response.getWriter().append(e.toString());
			 }
			 
			}else{
				response.sendRedirect("index.jsp");
			}	
	}

}
