package springboot.camel.api.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import springboot.camel.api.service.ProcessItemService;

@Component
public class ResponseProcessor  implements Processor {
	
	@Autowired
	ProcessItemService service;

	@Override
	public void process(Exchange exchange) throws Exception {
		try {
			String response= service.processItem(exchange.getIn().getBody(String.class));
			exchange.getIn().setBody((new ObjectMapper()).writeValueAsString(response));
			exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.OK.value());
		}catch(Exception ex) {
			throw new Exception();
		}

	}


	
}
