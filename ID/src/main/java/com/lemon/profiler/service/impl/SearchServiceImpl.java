package com.lemon.profiler.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lemon.profiler.model.Profile;
import com.lemon.profiler.model.Task;
import com.lemon.profiler.service.AuthenticationService;
import com.lemon.profiler.service.PropertyReaderService;
import com.lemon.profiler.service.SearchService;


public class SearchServiceImpl implements SearchService{
	PropertyReaderService propS = new PropertyReaderServiceImpl();
	AuthenticationService authService = new AuthenticationServiceImpl();
	
	private ArrayList<String> resultList;

    public ArrayList<String> getResultList() {
		return resultList;
	}
	public void setResultList(ArrayList<String> resultList) {
		this.resultList = resultList;
	}

	Log logger = LogFactory.getLog(this.getClass());
	
	@Override
	public List<Task> searchTask(Task task) {
		String ticket = authService.readTicket();
		List<Task> workList = new ArrayList<Task>();
		Task workNode;
		String strURL = propS.getKeyValue("contentServerURL")
				+ propS.getKeyValue("serviceURL")+"commonSearch";
		InputStream in3 = null;
		PostMethod post = new PostMethod(strURL);
		if (ticket != null && ticket.isEmpty()
				&& ticket.equals(authService.validateTicket(ticket))) {
			try {
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(strURL);
				method.addParameter("alf_ticket", ticket);
				int statusCode = client.executeMethod(method);
				System.out.println(statusCode);
				if (statusCode != -1) {
					in3 = method.getResponseBodyAsStream();
					DocumentBuilderFactory factory = DocumentBuilderFactory
							.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(in3);
					doc.getDocumentElement().normalize();
					TransformerFactory factory7 = TransformerFactory
							.newInstance();
					Transformer xform = factory7.newTransformer();
					xform.transform(new DOMSource(doc), new StreamResult(
							System.out));
					NodeList itemLst = doc.getElementsByTagName("tasks");
					try {
						for (int i = 0; i < itemLst.getLength(); i++) {
							Node item = itemLst.item(i);
							if (item.getNodeType() == Node.ELEMENT_NODE) {
								Element ielem = (Element) item;
								NodeList guidanceText = ielem
										.getElementsByTagName("taskItem");
								for (int gti = 0; gti < guidanceText
										.getLength(); gti++) {
									workNode = new Task();
									Node taskItem = guidanceText.item(gti);
									if (taskItem.getNodeType() == Node.ELEMENT_NODE) {
										Element taskelem = (Element) taskItem;
										workNode.setDescription(taskelem
												.getElementsByTagName(
														"description").item(0)
												.getTextContent());
										workNode.setType(taskelem
												.getElementsByTagName("type")
												.item(0).getTextContent());
										workNode.setTaskid(taskelem
												.getElementsByTagName("taskid")
												.item(0).getTextContent());
										workNode.setStartdate(taskelem
												.getElementsByTagName(
														"startdate").item(0)
												.getTextContent());
										workNode.setPriority(taskelem
												.getElementsByTagName(
														"priority").item(0)
												.getTextContent());
										workNode.setStatus(taskelem
												.getElementsByTagName("status")
												.item(0).getTextContent());
										workNode.setPercentcomplete(taskelem
												.getElementsByTagName(
														"percentcomplete")
												.item(0).getTextContent());
										workNode.setDuetoday(taskelem
												.getElementsByTagName(
														"duetoday").item(0)
												.getTextContent());
										workNode.setOverdue(taskelem
												.getElementsByTagName("overdue")
												.item(0).getTextContent());
										workNode.setDuedate(taskelem
												.getElementsByTagName("duedate")
												.item(0).getTextContent());
									}
									workList.add(workNode);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("total worklist size :" + workList.size());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		}
		return workList;
	}
	
	@Override
	public ArrayList<com.lemon.profiler.model.Node> searchText(String textToSearch, String pageNum, String pageSize) { 
		String ticket = authService.readTicket();
		String strURL = propS.getKeyValue("contentServerURL") + propS.getKeyValue("serviceURL")+"commonSearch";
		InputStream in3 = null;
		com.lemon.profiler.model.Node node = null;
		ArrayList<com.lemon.profiler.model.Node> workList = new ArrayList<com.lemon.profiler.model.Node>();
		PostMethod post = new PostMethod(strURL);
		try { 
			//if (ticket != null && ticket.isEmpty() && ticket.equals(authService.validateTicket(ticket))) { 
				HttpClient client = new HttpClient();
				PostMethod method = new PostMethod(strURL);
				method.addParameter("stringToSearch", textToSearch);
				System.out.println("Added String to search.. "+textToSearch);
				method.addParameter("alf_ticket", ticket);
				method.addParameter("pagenum", pageNum);
				method.addParameter("pagesize", pageSize);
				int statusCode = client.executeMethod(method);
				System.out.println(statusCode);
				if (statusCode != -1) {
					in3 = method.getResponseBodyAsStream();
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document doc = builder.parse(in3);
					doc.getDocumentElement().normalize();
					TransformerFactory factory7 = TransformerFactory.newInstance();
					Transformer xform = factory7.newTransformer();
					xform.transform(new DOMSource(doc), new StreamResult(System.out));
					NodeList itemLst = doc.getElementsByTagName("node");
					try {
//						for (int i = 0; i < itemLst.getLength(); i++) {
//							Node item = itemLst.item(i);
//							if (item.getNodeType() == Node.ELEMENT_NODE) {
//								Element ielem = (Element) item;
//								NodeList nodelist = ielem
//										.getElementsByTagName("node");
								for (int gti = 0; gti < itemLst
										.getLength(); gti++) {
									node = new com.lemon.profiler.model.Node();
									Node taskItem = itemLst.item(gti);
									if (taskItem.getNodeType() == Node.ELEMENT_NODE) {
										Element taskelem = (Element) taskItem;
										node.setId(taskelem.getElementsByTagName("id").item(0).getTextContent());
										node.setName(taskelem.getElementsByTagName("name").item(0).getTextContent()); 
										node.setCreatedDate(taskelem.getElementsByTagName("createdDate").item(0).getTextContent());
										node.setModifier(taskelem.getElementsByTagName("modifier").item(0).getTextContent());
										node.setModifiedDate(taskelem.getElementsByTagName("modifiedDate").item(0).getTextContent());
										node.setMimetype(taskelem.getElementsByTagName("mimetype").item(0).getTextContent());
										node.setSize(taskelem.getElementsByTagName("size").item(0).getTextContent());
										node.setAuthor(taskelem.getElementsByTagName("author").item(0).getTextContent());
										node.setPath(taskelem.getElementsByTagName("path").item(0).getTextContent());
										node.setIcon16(taskelem.getElementsByTagName("icon16").item(0).getTextContent());
										node.setIcon32(taskelem.getElementsByTagName("icon32").item(0).getTextContent()); 
									}
									workList.add(node);
									System.out.println("Added node "+node.getId());
								}
							//}
						//}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("total worklist size :" + workList.size());
				//}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		return workList;
	}
	@Override
	public List<Profile> searchProfile(Profile profile) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
