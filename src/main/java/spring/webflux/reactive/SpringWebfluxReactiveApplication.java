package spring.webflux.reactive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Instant;

@SpringBootApplication
public class SpringWebfluxReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxReactiveApplication.class, args);
	}

}

@RestController
class GreetingController{
	private final GreetingService greetingService;

	GreetingController(GreetingService greetingService) {
		this.greetingService = greetingService;
	}

	@GetMapping("/greeting/{name}")
	Mono<GreetingResponse> greet(@PathVariable String name){
		return this.greetingService.greet(new GreetingRequest(name));
	}
}

@Service
class GreetingService{
	private GreetingResponse greet(String name){
		return new GreetingResponse("Hello "+name+ "@"+Instant.now());
	}
	Mono<GreetingResponse> greet(GreetingRequest request){
		return Mono.just(greet(request.getName()));
	}
	
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class GreetingResponse{
	private String message;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class GreetingRequest{
	private String name;
}