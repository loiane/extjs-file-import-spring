package com.loiane.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.loiane.beans.FileUploadBean;

/**
 * Controller - Spring
 * 
 * @author Loiane Groner
 * http://loiane.com
 * http://loianegroner.com
 */
public class FileUploadController extends SimpleFormController  {

	protected ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors) throws ServletException, IOException {

		// cast the bean
		FileUploadBean bean = (FileUploadBean) command;

		MultipartFile file = bean.getFile();
		String fileName = null;

		if (file == null) {
			System.out.println("User Did not upload file");
		}
		else {
			System.out.println("Uploaded File Name is :" + file.getOriginalFilename());
		}

		InputStream inputStream = null;
		OutputStream outputStream = null;
		if (file.getSize() > 0) {
			inputStream = file.getInputStream();
			String root = "C:\\";
			fileName = root + file.getOriginalFilename();
			outputStream = new FileOutputStream(fileName);
			int readBytes = 0;
			byte[] buffer = new byte[10000];
			while ((readBytes = inputStream.read(buffer, 0 , 10000))!=-1){

				outputStream.write(buffer, 0, readBytes);
			}
			outputStream.close();
			inputStream.close();
		}

		return null;

	}     

	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
	throws ServletException {
		// to actually be able to convert Multipart instance to byte[]
		// we have to register a custom editor
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		// now Spring knows how to handle multipart object and convert them
	}

}
