package io.openleap.mrs.seeder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MrsSeederApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MrsSeederApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }
}