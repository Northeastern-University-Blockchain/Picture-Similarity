package com.spring.rest.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import imgMatch.test;

/**
 * Handles requests for the application file upload requests
 * 
 */
@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	/**
	 * Upload multiple file using Spring Controller
	 * 
	 * @param rootPath
	 *            file will be saved in local disk E
	 * @param dir
	 *            makes a directory "tmpfiles" in local disk E
	 * @param name
	 *            represented file name
	 * @param file
	 *            represented multiple file objects
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		
		String []add=new String[2];
		test startMatch=new test();
		
		if (files.length <2 || names.length<2)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			
			//System.out.println("1");
			logger.info(files[i].getName());
			
			
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = "e:";
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location=" + serverFile.getAbsolutePath());

				message = message + "You successfully uploaded pic " + name + "<br />";
			
				add[i]="e:"+File.separator + "tmpFiles"+File.separator +name;
				
				
			} catch (IOException IoException) {
				return "You failed to upload " + name + " => " + IoException.getMessage();
			}catch (Exception exception) {
				return "You failed to upload " + name + " => " + exception.getMessage();
			}
		}
	   
		message= message+startMatch.start(add[0],add[1]);
		return message;
	}
}
