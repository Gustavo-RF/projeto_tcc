package com.projeto.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {
		
	@RequestMapping("/")
	public ModelAndView index() throws ServletException,IOException{
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	@GetMapping("/resultado")
	public void function( @Valid @RequestBody String search, Errors errors,HttpServletResponse response) throws IOException{
		System.out.println(search);

		//ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		//FileWriter file = new FileWriter(classloader.getResource("files/teste.txt").getFile());
		File file = new ClassPathResource("teste.txt").getFile();
		PrintWriter pw = new PrintWriter(file);
		
		pw.println(search);
		pw.flush();
		pw.close();
		String mimeType= "text/plain";
        response.setContentType(mimeType);
	    response.setHeader("Content-Disposition", String.format("attachment; filename=teste.txt"));
	    response.setContentLength((int)file.length());
	    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	    FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
}
