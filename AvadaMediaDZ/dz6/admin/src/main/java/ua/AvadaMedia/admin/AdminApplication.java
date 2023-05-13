package ua.AvadaMedia.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminApplication {

	public static void main(String[] args) {
		System.out.println("main page: http://127.0.0.1:8000/");
		SpringApplication.run(AdminApplication.class, args);
	}

}
