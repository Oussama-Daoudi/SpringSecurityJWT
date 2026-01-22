package com.example.security;

import com.example.security.auth.AuthenticationService;
import com.example.security.auth.RegisterRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.security.user.Role.ADMIN;
import static com.example.security.user.Role.USER;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "School Management System API",
                version = "1.0.0",
                description = "Spring Boot 3 + Spring Security 6 + JWT Application for managing students, classrooms and clubs",
                contact = @Contact(
                        name = "Your Name",
                        email = "your.email@example.com",
                        url = "https://github.com/yourusername"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                ),
                termsOfService = "https://example.com/terms"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8081",
                        description = "Local Development Server"
                ),
                @Server(
                        url = "https://api.yourdomain.com",
                        description = "Production Server"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            // Optional: Create default users on startup
            // Uncomment and customize if needed

            /*
            var admin = RegisterRequest.builder()
                .firstname("Admin")
                .lastname("Admin")
                .email("admin@mail.com")
                .password("password")
                .role(ADMIN)
                .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());

            var user = RegisterRequest.builder()
                .firstname("User")
                .lastname("User")
                .email("user@mail.com")
                .password("password")
                .role(USER)
                .build();
            System.out.println("User token: " + service.register(user).getAccessToken());
            */
        };
    }
}