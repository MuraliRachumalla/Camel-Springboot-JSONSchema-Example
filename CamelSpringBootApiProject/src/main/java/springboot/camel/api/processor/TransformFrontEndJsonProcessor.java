package springboot.camel.api.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springboot.camel.api.model.BackendItem;
import springboot.camel.api.service.ProcessItemService;

@Component
public class TransformFrontEndJsonProcessor implements Processor {
	
	@Autowired
	ProcessItemService service;

	@Override
	public void process(Exchange exchange) throws Exception {
		try {
		BackendItem response= service.transformItem(exchange.getIn().getBody(String.class));
		exchange.getIn().setBody(response);
		}catch(Exception ex) {
			throw new Exception();
		}

	}

}
