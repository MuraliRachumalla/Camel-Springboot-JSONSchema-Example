package springboot.camel.api.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ErrorProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		exchange.getIn().setBody((new ObjectMapper()).writeValueAsString("Json Schema validation failed"));
		exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.BAD_REQUEST.value());
	}

}
