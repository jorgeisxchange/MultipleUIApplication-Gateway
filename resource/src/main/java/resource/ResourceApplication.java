package resource;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableRedisHttpSession
public class ResourceApplication extends WebSecurityConfigurerAdapter {
	
	String message = "Hello World";
	//Map<String, Object> changes = new HashMap<String, Object>();
	List<Map<String,Object>> changes = new ArrayList<Map<String,Object>>();
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public Map<String, Object> home() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", message);
		
		return model;
	}
	
/*	@RequestMapping(value="/changes", method=RequestMethod.POST)
	public Map<String, Object> changes() {
		return changes;
	}*/
	
	@RequestMapping(value="/changes", method=RequestMethod.POST)
	public List<Map<String, Object>> changes() {
		return changes;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public Map<String, Object> update(@RequestBody Map<String, Object> map, Principal principal) {
		if(map.containsKey("content")) {
			message = map.get("content").toString();
			
			Map<String, Object> newMap = new HashMap<String, Object>();
			
			newMap.put("timestamp", new Date());
			newMap.put("user", principal.getName());
			newMap.put("content", message);
			
			changes.add(newMap);
						
			if(changes.size() > 10) {
				//groovy changes = changes[0..9]
				changes = changes.subList(0, 9);
			}
			
			
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", message);
		
		return model;
	}

	
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {
    	http.httpBasic().disable();
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/**").hasRole("WRITER").anyRequest().authenticated();
    
    }
}
