package com.lemon.profiler.service;

import java.io.File;
import java.io.IOException;

import com.lemon.profiler.exceptions.ConnectionFailedException;

public interface AlfrescoFileService {
	public String uploadDocument(String profileId, File file, String fileName, String filesDirectory) throws ConnectionFailedException, IOException;
}
