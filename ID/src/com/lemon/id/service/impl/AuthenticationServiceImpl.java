package com.lemon.id.service.impl;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.lemon.id.service.AuthenticationService;
import com.lemon.id.service.PropertyReaderService; 

public class AuthenticationServiceImpl implements AuthenticationService{
	
	public static AuthenticationServiceImpl authenticationService;
	PropertyReaderService propS = PropertyReaderServiceImpl.getInstance();
	public AuthenticationServiceImpl getInstance(){
		if(authenticationService!=null)
			return authenticationService;
		else
			return new AuthenticationServiceImpl();
	}

	
//	//Authenticate from remote server
//	public String authenticate(){
//		return "success";
//	}
	
	public String validateTicket(String ticket){
		URL url = null;				
		HttpURLConnection connection = null;
		try {  
			String adminTicket = readTicket();
			String query = String.format("alf_ticket=%s",
					URLEncoder.encode(adminTicket, "UTF-8"));
			url = new URL(propS.getKeyValue("contentServerURL")+ "alfresco/service/api/login/ticket/"+ ticket+ "?"+query);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.connect();
			InputStream is = connection.getInputStream();
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document xmlDoc = docBuilder.parse(is);
			System.out.println("Validated Ticket =>" + xmlDoc.getElementsByTagName("ticket").item(0).getTextContent());
			return xmlDoc.getElementsByTagName("ticket").item(0).getTextContent();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;		
	}
	
	public String readTicket() {
		System.out.println("Trying to readTicket()");
		URL url = null;
		HttpURLConnection connection = null;
		try { 
			String query = String.format("u=%s&pw=%s",
					URLEncoder.encode(propS.getKeyValue("adminuser"), "UTF-8"),
					URLEncoder.encode(propS.getKeyValue("adminpassword"), "UTF-8"));
			url = new URL(propS.getKeyValue("contentServerURL")+"alfresco/service/api/login?"
					+ query);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.connect(); 
			InputStream is = connection.getInputStream();
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document xmlDoc = docBuilder.parse(is);
			System.out.println("Ticket =>"
					+ xmlDoc.getElementsByTagName("ticket").item(0)
							.getTextContent());
			return xmlDoc.getElementsByTagName("ticket").item(0)
					.getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}
	
	public String authenticate(String username, String password) {
		System.out.println("Trying to readTicket()");
		URL url = null;
		HttpURLConnection connection = null;
		try { 
			String query = String.format("u=%s&pw=%s",
					URLEncoder.encode(username, "UTF-8"),
					URLEncoder.encode(password, "UTF-8"));
			url = new URL(propS.getKeyValue("contentServerURL")+"alfresco/service/api/login?"
					+ query);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			connection.connect(); 
			InputStream is = connection.getInputStream();
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document xmlDoc = docBuilder.parse(is);
			System.out.println("Ticket =>"
					+ xmlDoc.getElementsByTagName("ticket").item(0)
							.getTextContent());
			return xmlDoc.getElementsByTagName("ticket").item(0).getTextContent();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}
	public boolean invalidatelogin(String ticket){
		URL url = null;
		HttpURLConnection connection = null;
		try{
			String adminTicket = readTicket();
			String query = String.format("alf_ticket=%s",
					URLEncoder.encode(adminTicket, "UTF-8"));
			url = new URL(propS.getKeyValue("contentServerURL")+ "alfresco/service/api/login/ticket/"+ ticket+ "?"+query);
			
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded" );
			connection.setRequestMethod("DELETE");
			int responseCode = connection.getResponseCode();
						
			connection.connect();
			System.out.println("<<<<>>>>" +responseCode);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (connection != null) {
				connection.disconnect();
			}
		}		 
		return true;
	}
	
//	public boolean hasValidTicket(){
//		String ticket = (String)ServletContext.getContext().getSession().get("alf_ticket"); 
//		if(ticket==null || ticket.isEmpty()){
//			return false;
//		}else{
//			return ticket.equals(validateTicket(ticket));
//		}
//	}
//	
//	public String getSessionTicket(){
//		String ticket = (String)ActionContext.getContext().getSession().get("alf_ticket"); 
//		System.out.println("Returing session ticket : "+ticket);
//		return ticket; 
//	}
}
