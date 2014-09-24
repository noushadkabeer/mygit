package com.lemon.id.service;

import java.io.File;
import java.io.IOException;

import com.lemon.id.exception.ConnectionFailedException;



public interface AlfrescoContentService {
	public String submitDetails(String username, String address, String details) throws ConnectionFailedException, IOException;
	public String uploadDocument(String profileId, File file, String fileName, String filesDirectory) throws ConnectionFailedException, IOException;
}
