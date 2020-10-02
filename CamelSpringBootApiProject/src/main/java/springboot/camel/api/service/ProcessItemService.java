package springboot.camel.api.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import springboot.camel.api.model.BackendItem;

@Service
public class ProcessItemService {
	
	public String processItem(String item) {
		return "Received Item price";
	}

	public BackendItem transformItem(String item) throws IOException {
		System.out.println(item);
		JsonNode jobj = (new ObjectMapper()).readTree(item);
	
		BackendItem bItem = new BackendItem(jobj.get("id").asInt(),jobj.get("name").asText(),jobj.get("price").asDouble());
		return bItem;
	}
	

}
