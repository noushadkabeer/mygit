package com.lemon.profiler.common;

import java.util.Random;

import org.w3c.dom.Node;

import com.lemon.profiler.service.AlfrescoFileService;
import com.lemon.profiler.service.PropertyReaderService;
import com.lemon.profiler.service.impl.AuthenticationServiceImpl;
import com.lemon.profiler.service.impl.FileUploadImpl;
import com.lemon.profiler.service.impl.PropertyReaderServiceImpl;

public class ProfilerUtil {
	AlfrescoFileService fileService = new FileUploadImpl();
	PropertyReaderService propertyService = new PropertyReaderServiceImpl();
	
	public ProfilerUtil(){}	
	public static ProfilerUtil profileUtil;
	
	public static ProfilerUtil getInstance(){
		if(profileUtil!=null)
			return profileUtil;
		else
			return new ProfilerUtil();
	}
	public String generatedInteger(){
		return (new Random().nextInt(Integer.MAX_VALUE)+1)+"" ;		
	}	
	
	public String serviceURL(){
		return propertyService.getKeyValue("contentServerURL") + propertyService.getKeyValue("serviceURL");		
	}
	
	public String alfrescoContextURL(){
		return propertyService.getKeyValue("contentServerURL") + propertyService.getKeyValue("alfrescocontext");
	}
	 
	public static boolean notEmpty(String s) {
		 return (s != null && s.length() > 0);
	}
	
	public static boolean nodeNotEmpty(Node n) {
		 return (n != null);
	}
}
