package com.devcodestack.core.concepts.aws;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaHandler implements RequestHandler<Map<String, Object>, String> {

	@Override
	public String handleRequest(Map<String, Object> input, Context context) {
		System.out.println("After all scenario's...");
		System.out.println(input);
		System.out.println("Body received:" + input.get("body"));
		System.out.println("Context received: " + context.getFunctionName());
		
		return "Completed";
	}

}
