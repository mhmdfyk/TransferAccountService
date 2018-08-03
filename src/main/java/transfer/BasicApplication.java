package transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Make sure that your main class is in a root package above other classes.
   When you run a Spring Boot Application, (i.e. a class annotated with @SpringBootApplication), 
   Spring will only scan the classes below your main class package.
 * @author REACH_MFAYEK
 */
@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}
}
