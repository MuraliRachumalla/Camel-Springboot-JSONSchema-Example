package springboot.camel.api.resource;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import springboot.camel.api.processor.ErrorProcessor;
import springboot.camel.api.processor.ResponseProcessor;
import springboot.camel.api.processor.TransformFrontEndJsonProcessor;

@Component
public class ApplicationResource extends RouteBuilder {
	
	@Autowired
	TransformFrontEndJsonProcessor processor;
	
	@Autowired
	ResponseProcessor responseProcessor;
	
	@Autowired
	ErrorProcessor errorProcessor;

	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").port(9091).host("localhost")
		.bindingMode(RestBindingMode.auto)
			.clientRequestValidation(true).dataFormatProperty("prettyPrint", "true");

		rest("/api").post("/post-price")
		.consumes(MediaType.APPLICATION_JSON_VALUE)
		.produces(MediaType.APPLICATION_JSON_VALUE)
		.bindingMode(RestBindingMode.off)
		.to("direct:transformValidateAndProcess")
		;
		
				
		from("direct:transformValidateAndProcess")
		.onException(Exception.class).process(errorProcessor).handled(true).end()
		.to("log:?level=INFO&showBody=true")
		.log("${body}")
		.convertBodyTo(String.class)
		.to("json-validator:frontendschema.json")  //validate front end json against frontendschema.json
		.process(processor)
		.to("log:?level=INFO&showBody=true")
		.log("${body}")
		.marshal().json(JsonLibrary.Jackson)
		.to("log:?level=INFO&showBody=true")
		.log("${body}")
		.convertBodyTo(String.class)
		.to("json-validator:backendschema.json")   //validate backend json against backendschema.json
		.process(responseProcessor)
		;
		

	}

}
