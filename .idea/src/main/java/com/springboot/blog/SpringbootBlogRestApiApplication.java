package com.springboot.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Blog App Rest APIs",
				description = "SpringBoot Blog App Rest APIs Documentations",
				version = "v1.0",
				contact = @Contact(
						name = "Stella",
						email = "stella@gmail.com",
						url = "https://www.stella.net"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.stella.net/license"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Blog App Documentations",
				url = "https://github.com/YixuanChen-Stella/BlogApp"
		)
)
public class SpringbootBlogRestApiApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {

		Role adminRole = new Role();
		adminRole.setName("ROLE-ADMIN");
		roleRepository.save(adminRole);

		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		roleRepository.save(userRole);
	}
}
