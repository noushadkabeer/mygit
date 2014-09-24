package com.lemon.profiler.service.impl;

import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory; 
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.lemon.profiler.model.Attachment;
import com.lemon.profiler.model.Profile;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.ProfileService;
import com.lemon.profiler.service.PropertyReaderService;
import com.lemon.profiler.common.ProfilerUtil;  

public class ProfileServiceImpl implements ProfileService{

	PropertyReaderService propS = new PropertyReaderServiceImpl();
	AuthenticationService authService = new AuthenticationServiceImpl();
	private static ArrayList<Profile> profileList;
	
	static {
		profileList = new ArrayList<Profile>();
		profileList.add(new Profile("001","Name","Experience","B.Sc","Skills","Interest","Abudhabi","SO14 OAT","Resume Summary", new Attachment("id", "attach", "ds", "DD")));
		profileList.add(new Profile("002","Name2","Experience","B.Sc","Skills","Interest","Abudhabi","SO14 OAT","Resume Summary", new Attachment("id1", "attachs", "ds", "DD")));		
	}

    Log logger = LogFactory.getLog(this.getClass());
	
	@Override
	public List<Profile> getAllProfiles() {		 
		String luceneQuery = "PATH:\"/app:company_home/cm:profiler//*\" AND TYPE:\"pf:profile\"";
		return searchInRepository(luceneQuery, "", "", false);		
	}

	@Override
	public Profile getProfileByName(String name) {
		String luceneQuery = "PATH:\"/app:company_home/cm:profiler//*\" AND TYPE:\"pf:profile\" AND @cm:name:"+name+"\"";
		List<Profile> profiles = searchInRepository(luceneQuery, "undefined", "undefined", false);
		Profile profile = profiles.get(0);
		// TODO Auto-generated method stub
		return profile;
	}
	@Override
	public Profile getProfile(String id) { 
		List<Profile> profiles = new ArrayList<Profile>(); 
		String strURL = ProfilerUtil.getInstance().serviceURL()+ "findProfileById";
		InputStream in4 = null;
		Profile profile = new Profile();  
		PostMethod post = new PostMethod(strURL);
		String ticket = authService.readTicket();
		System.out.println("Ticket Prepared & ready to Search :"+id);
		if (ticket != null && !ticket.isEmpty()
				&& ticket.equals(authService.validateTicket(ticket))) {
			try {
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(strURL);
				// Add any parameter if u want to send it with Post req. 
				method.addParameter("stringToSearch", id); 
				// method.addParameter("u", "admin");
				// method.addParameter("p", "admin");
				method.addParameter("alf_ticket", ticket); 
				int statusCode = client.executeMethod(method);
				System.out.println(statusCode);
				if (statusCode != -1) {
					in4 = method.getResponseBodyAsStream();
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(in4);
					System.out.println("Bfr normalize \n"
							+ doc.getTextContent());
					doc.getDocumentElement().normalize();
					System.out.println("Just normalized \n"
							+ doc.getTextContent());
					TransformerFactory factory7 = TransformerFactory
							.newInstance();
					Transformer xform = factory7.newTransformer();
					xform.transform(new DOMSource(doc), new StreamResult(System.out));
					System.out.println(doc.getTextContent());
					NodeList itemLst = doc.getElementsByTagName("profile"); 
					NodeList attachmentLst;
					Node attachment;
					for (int i = 0; i < itemLst.getLength(); i++) {
						Node node = itemLst.item(i);
						profile = new Profile();
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element) itemLst.item(i);
							System.out.println(element
									.getElementsByTagName("profileId").item(0)
									.getTextContent());
							profile.setId(element
									.getElementsByTagName("id").item(0)
									.getTextContent());								
							profile.setName(element
									.getElementsByTagName("profileName")
									.item(0).getTextContent());
							profile.setExperience(element
									.getElementsByTagName("profileExperience").item(0)
									.getTextContent());
							profile.setEducation(element
									.getElementsByTagName("profileEducation")
									.item(0).getTextContent());
							profile.setSkills(element
									.getElementsByTagName("profileSkills")
									.item(0).getTextContent());
							profile.setInterests(element
									.getElementsByTagName("profileInterests")
									.item(0).getTextContent());
							profile.setLocation(element
									.getElementsByTagName("profileLocation")
									.item(0).getTextContent());
							profile.setAddress(element
									.getElementsByTagName("profileAddress")
									.item(0).getTextContent());
							profile.setResumeSummary(element
									.getElementsByTagName("profileSummary")
									.item(0).getTextContent());
							attachmentLst = doc.getElementsByTagName("attachments");  
							for (int j = 0; j < attachmentLst.getLength(); j++) {
								attachment = attachmentLst.item(j);
								if (attachment.getNodeType() == Node.ELEMENT_NODE) {
									Element element2 = (Element) attachmentLst.item(i);

									String sid = ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("id").item(0))
											?element2.getElementsByTagName("id").item(0).getTextContent():"";
									String name =ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("name").item(0))
											?element2.getElementsByTagName("name").item(0).getTextContent():"";
									String title = ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("title").item(0))
											?element2.getElementsByTagName("title").item(0).getTextContent():"";
									String url=ProfilerUtil.getInstance().alfrescoContextURL()+ (ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("url").item(0))	?element2.getElementsByTagName("url").item(0).getTextContent():"");
									profile.setAttachments(new Attachment(sid,name, title,url));
								}
							}							
							
							profiles.add(profile);
						}
					} 
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		}
		System.out.println("Total Profiles on Search by ID Returned :"+profiles.size());
			
		profile = profiles.get(0);
		// TODO Auto-generated method stub
		return profile;
	}
	

	@Override
	public void update(Profile profile) {
		System.out.println("will update this..");
        
        //From here
        String ticket = authService.readTicket();
        String strURL =  ProfilerUtil.getInstance().serviceURL()+ "updateProfile?alf_ticket="+ticket;  
		PostMethod post = new PostMethod(strURL); 
		
		post.addParameter("alf_ticket", ticket);
		try { 
			InputStream in2 = null;
			String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><storeid>"+profile.getId()+"</storeid>"; 
			System.out.println("Xml : " + xmlString);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document document = null;
			document = builder.parse(is);
			String message = document.getDocumentElement().getTextContent();
			System.out.println("Got the msg and displaying it :" + message); 
			Part[] parts = { new StringPart("id", profile.getId()),
					new StringPart("education", profile.getEducation()),
					new StringPart("experience", profile.getExperience()),
					new StringPart("interests", profile.getInterests()),
					new StringPart("skills", profile.getSkills()),
					new StringPart("name", profile.getName()),
					new StringPart("location", profile.getLocation()),
					new StringPart("address", profile.getAddress()),
					new StringPart("resumeSummary", profile.getResumeSummary())		
			};

			System.out.println(parts.length + parts.toString());
			HttpClient client = new HttpClient();
			// PostMethod method = new PostMethod(strURL);
//			Credentials loginCreds = new UsernamePasswordCredentials("admin","admin");
//			client.getState().setCredentials(AuthScope.ANY, loginCreds);
			
			
			post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));
			post.setRequestEntity(new MultipartRequestEntity(parts, post
					.getParams()));
			System.out.println(post);
			int statusCode = client.executeMethod(post);
			System.out.println(statusCode);
			if (statusCode != -1) {
				in2 = post.getResponseBodyAsStream();

				DocumentBuilderFactory factory2 = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder2 = factory2.newDocumentBuilder();
				Document doc3 = builder2.parse(in2);
				System.out.println(in2 + "  -------"); 
				TransformerFactory factory7 = TransformerFactory.newInstance();
				Transformer xform = factory7.newTransformer(); 
				xform.transform(new DOMSource(doc3), new StreamResult(
						System.out));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}        
	}

	@Override
	public void insert(Profile profile) {
		
		String ticket = authService.readTicket();	
		 String strURL =  ProfilerUtil.getInstance().serviceURL()+ "createProfile?alf_ticket="+ticket;  
			PostMethod post = new PostMethod(strURL); 
			System.out.println("Creating a new profile "+strURL);
			//Before insert generate an unique id for the record
			
			profile.setId(ProfilerUtil.getInstance().generatedInteger());	
			//post.addParameter("alf_ticket", ticket);
			try { 
				InputStream in2 = null;
				String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><storeid>"+profile.getId()+"</storeid>"; 
				System.out.println("Xml : " + xmlString);
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder;
				builder = factory.newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(xmlString));
				Document document = null;
				document = builder.parse(is);
				String message = document.getDocumentElement().getTextContent();
				System.out.println("Got the msg and displaying it :" + message); 
				Part[] parts = { new StringPart("id", profile.getId()),
						new StringPart("education", profile.getEducation()),
						new StringPart("experience", profile.getExperience()),
						new StringPart("interests", profile.getInterests()),
						new StringPart("skills", profile.getSkills()),
						new StringPart("name", profile.getName()),
						new StringPart("location", profile.getLocation()),
						new StringPart("address", profile.getAddress()),
						new StringPart("resumeSummary", profile.getResumeSummary())
				};

				System.out.println(parts.length + parts.toString()+ profile.getAddress()+profile.getResumeSummary());
				HttpClient client = new HttpClient();
				// PostMethod method = new PostMethod(strURL);
				//Credentials loginCreds = new UsernamePasswordCredentials("admin",	"admin");
				//client.getState().setCredentials(AuthScope.ANY, loginCreds);
				
				post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
						new DefaultHttpMethodRetryHandler(3, false));
				post.setRequestEntity(new MultipartRequestEntity(parts, post.getParams()));
				int statusCode = client.executeMethod(post);
				System.out.println(statusCode);
				if (statusCode != -1) {
					in2 = post.getResponseBodyAsStream();
					DocumentBuilderFactory factory2 = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder builder2 = factory2.newDocumentBuilder();
					Document doc3 = builder2.parse(in2);
					System.out.println(in2 + "  -------"); 
					TransformerFactory factory7 = TransformerFactory.newInstance();
					Transformer xform = factory7.newTransformer(); 
					xform.transform(new DOMSource(doc3), new StreamResult(
							System.out));
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}        		
		
		System.out.println("Added one and now size is :"+profileList.size());		
		
	}

	@Override
	public String delete(String id) {
		String result = "success";
		System.out.println("Deleting the Profile :"+id);         
		//From here
        String ticket = authService.readTicket();
        String strURL =  ProfilerUtil.getInstance().serviceURL()+ "deleteProfile?alf_ticket="+ticket;  
		PostMethod post = new PostMethod(strURL); 		
		post.addParameter("alf_ticket", ticket);
		try { 
			InputStream in2 = null;
			String xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?><storeid>"+id+"</storeid>"; 
			System.out.println("Xml : " + xmlString);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlString));
			Document document = null;
			document = builder.parse(is);
			String message = document.getDocumentElement().getTextContent();
			System.out.println("Got the msg and displaying it :" + message); 
			Part[] parts = { new StringPart("id", id),
					new StringPart("id", id)				
			}; 
			HttpClient client = new HttpClient(); 			
			post.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));
			post.setRequestEntity(new MultipartRequestEntity(parts, post
					.getParams()));
			System.out.println(post);
			int statusCode = client.executeMethod(post);
			System.out.println("Status after delete :"+statusCode);
			if (statusCode != -1) {
				in2 = post.getResponseBodyAsStream();
				DocumentBuilderFactory factory2 = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder2 = factory2.newDocumentBuilder();
				Document doc3 = builder2.parse(in2);
				System.out.println(in2 + "  -------"); 
				TransformerFactory factory7 = TransformerFactory.newInstance();
				Transformer xform = factory7.newTransformer(); 
				xform.transform(new DOMSource(doc3), new StreamResult(
						System.out));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}     
		
		return result;
	}

	@Override
	public List<Profile> searchProfile(String searchString, String pageNum, String pageSize) {
		return searchInRepository(searchString, pageNum, pageSize, true);
	}
	
	public List<Profile> searchInRepository(String searchString, String pageNum, String pageSize, boolean plainSearch){
		List<Profile> profiles = new ArrayList<Profile>(); 
		String strURL = ProfilerUtil.getInstance().serviceURL()+ "findProfile";
		InputStream in4 = null;
		Profile profile = new Profile();  
		PostMethod post = new PostMethod(strURL);
		String ticket = authService.readTicket();
		System.out.println("Ticket Prepared & ready to Search :"+searchString);
		if (ticket != null && !ticket.isEmpty()
				&& ticket.equals(authService.validateTicket(ticket))) {
			try {
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(strURL);
				// Add any parameter if u want to send it with Post req.
				if(plainSearch)
					method.addParameter("stringToSearch", searchString);
				else
					method.addParameter("lucenequery", searchString);
				// method.addParameter("u", "admin");
				// method.addParameter("p", "admin");
				method.addParameter("alf_ticket", ticket);
				method.addParameter("pagenum", pageNum);
				method.addParameter("pagesize", pageSize);
				int statusCode = client.executeMethod(method);
				System.out.println(statusCode);
				if (statusCode != -1) {
					in4 = method.getResponseBodyAsStream();
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(in4);
					System.out.println("Bfr normalize \n"
							+ doc.getTextContent());
					doc.getDocumentElement().normalize();
					System.out.println("Just normalized \n"
							+ doc.getTextContent());
					TransformerFactory factory7 = TransformerFactory
							.newInstance();
					Transformer xform = factory7.newTransformer();
					xform.transform(new DOMSource(doc), new StreamResult(System.out));
					System.out.println(doc.getTextContent()); 
					NodeList itemLst = doc.getElementsByTagName("profile"); 
					NodeList attachmentLst;
					Node attachment;
					for (int i = 0; i < itemLst.getLength(); i++) {
						Node node = itemLst.item(i);
						profile = new Profile();
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element element = (Element) itemLst.item(i);
							System.out.println(element
									.getElementsByTagName("profileId").item(0)
									.getTextContent());
							profile.setId(element
									.getElementsByTagName("id").item(0)
									.getTextContent());								
							profile.setName(element
									.getElementsByTagName("profileName")
									.item(0).getTextContent());
							profile.setExperience(element
									.getElementsByTagName("profileExperience").item(0)
									.getTextContent());
							profile.setEducation(element
									.getElementsByTagName("profileEducation")
									.item(0).getTextContent());
							profile.setSkills(element
									.getElementsByTagName("profileSkills")
									.item(0).getTextContent());
							profile.setInterests(element
									.getElementsByTagName("profileInterests")
									.item(0).getTextContent());
							profile.setLocation(element
									.getElementsByTagName("profileLocation")
									.item(0).getTextContent());
							profile.setAddress(element
									.getElementsByTagName("profileAddress")
									.item(0).getTextContent());
							profile.setResumeSummary(element
									.getElementsByTagName("profileSummary")
									.item(0).getTextContent());
							attachmentLst = doc.getElementsByTagName("attachments");  
							for (int j = 0; j < attachmentLst.getLength(); j++) {
								attachment = attachmentLst.item(j);
								if (attachment.getNodeType() == Node.ELEMENT_NODE) {
									Element element2 = (Element) attachmentLst.item(i);
									
									String id = ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("id").item(0))
											?element2.getElementsByTagName("id").item(0).getTextContent():"";
									String name =ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("name").item(0))
											?element2.getElementsByTagName("name").item(0).getTextContent():"";
									String title = ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("title").item(0))
											?element2.getElementsByTagName("title").item(0).getTextContent():"";
									String url=ProfilerUtil.getInstance().alfrescoContextURL()+ (ProfilerUtil.nodeNotEmpty(element2.getElementsByTagName("url").item(0))
											?element2.getElementsByTagName("url").item(0).getTextContent():"");
									profile.setAttachments(new Attachment(id,name, title,url));
								}
							}							
							
							
							profiles.add(profile);
						}
					} 
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		}
		System.out.println("Before :"+profiles.size());
		return profiles;
	}
	
}
