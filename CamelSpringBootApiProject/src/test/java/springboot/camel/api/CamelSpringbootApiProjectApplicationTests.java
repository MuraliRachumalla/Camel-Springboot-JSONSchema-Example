package springboot.camel.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CamelSpringbootApiProjectApplicationTests {
	 @Autowired
	 private TestRestTemplate restTemplate;
		
	@Test
	public void testPostPriceRequestSuccess() {	
	
	   String jsonRequest = "{\n" + 
	   		"	\"id\":1234,\n" + 
	   		"	\"name\":\"Murali\",\n" + 
	   		"	\"price\":3.8\n" + 
	   		"}";
	    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
	    headers.add("Content-Type", "application/json");
		HttpEntity<String> request = 
			      new HttpEntity<String>(jsonRequest, headers);	
	   ResponseEntity<String> response = restTemplate.postForEntity("/api/post-price", request, String.class);
	   assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	   String s = response.getBody();
	   assertThat(s.equals("Received Item price"));
	}
	
	@Test
	public void testPostPriceRequestFailed() {		
	
	   String jsonRequest = "{}";
	    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
	    headers.add("Content-Type", "application/json");
		HttpEntity<String> request = 
			      new HttpEntity<String>(jsonRequest, headers);	
	   ResponseEntity<String> response = restTemplate.postForEntity("/api/post-price", request, String.class);
	   assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	   String s = response.getBody();
	   assertThat(s.equals("Json Schema validation failed"));
	}

}
