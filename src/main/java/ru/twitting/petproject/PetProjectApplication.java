package ru.twitting.petproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.twitting.petproject.config.property.LogbookProperties;

@EnableConfigurationProperties(
		value = {
				LogbookProperties.class
		}
)
@SpringBootApplication
public class PetProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetProjectApplication.class, args);
	}

}
