package com.loiane.beans;

import org.springframework.web.multipart.MultipartFile;

/**
 * Represents file uploaded from extjs form
 * 
 * @author Loiane Groner
 * http://loiane.com
 * http://loianegroner.com
 */
public class FileUploadBean {
	
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
}
