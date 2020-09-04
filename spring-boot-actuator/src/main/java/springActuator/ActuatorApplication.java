package springActuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ActuatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActuatorApplication.class, args);
	}

	@RequestMapping("/hello")
	public String index() {
		return "Hello World";
	}
}

@Component
class MyHealthIndicator implements HealthIndicator {

	@Value("${endpoint.mycustomhealth:1}")
	private int upCode;

	@Value("${upcode-message:0}")
	private int downCode;

	@Override
	public Health health() {
		if (upCode != 0) {
			return Health.down().withDetail("Error Code", upCode).build();
		}
		return Health.up().build();
	}
}
