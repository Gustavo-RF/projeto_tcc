package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Retorno;

@RestController
public class MainController {
	
	@RequestMapping("/")
	public ModelAndView index() throws ServletException,IOException{
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	@PostMapping("/resultado")
	public ResponseEntity<?> function( @Valid @RequestBody String search, Errors errors,HttpServletResponse response) throws IOException{
		Retorno retorno = new Retorno();
		
		if (errors.hasErrors()) {
            retorno.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(retorno);
        }
		
		File xls = new File("teste.txt"); // or whatever your file is
		FileInputStream in = new FileInputStream(xls);
		OutputStream out = response.getOutputStream();

		byte[] buffer= new byte[8192]; // use bigger if you want
		int length = 0;

		while ((length = in.read(buffer)) > 0){
		     out.write(buffer, 0, length);
		}
		in.close();
		out.close();

		retorno.setMsg(search);
		System.out.println(search);
		return ResponseEntity.ok(retorno);
	}	
}
