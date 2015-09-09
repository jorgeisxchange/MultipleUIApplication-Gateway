package resource;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableRedisHttpSession
public class ResourceApplication {
	
	@RequestMapping("/")
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World");
		
		return model;
	}	
	
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }
    
    
    @Bean
    public HeaderHttpSessionStrategy sessionStrategy() {
    	return new HeaderHttpSessionStrategy();
    }
}
