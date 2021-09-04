package com.dash.dash;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
public class DashboardApplication {



	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper()
	{
		return  new ModelMapper();
	}

}


