package ma.elmakhloufi.springaop;

import ma.elmakhloufi.springaop.aspects.ApplicationContext;
import ma.elmakhloufi.springaop.metier.IMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.awt.*;

@SpringBootApplication
public class SpringAopApplication {
	public static void main(String[] args) {

		ApplicationContext.authenticateUser("root","1234",new String[]{"ADMIN"});
		SpringApplication.run(SpringAopApplication.class, args);

	}

	@Bean
	CommandLineRunner runner(IMetier metier) {
		return args -> {
			System.out.println("Hello");
			metier.process();
			metier.compute();
		};
	}

}
