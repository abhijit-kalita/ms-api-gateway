package com.sg.microservices.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;

@Component
public class ZuulFilterApp extends ZuulFilter {
	
	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = true;
	
	private static final Logger eslogger = LoggerFactory.getLogger("es-logger");
	
	
	@Autowired
	Tracer tracer;

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}

	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}

	@Override
	public Object run() {
//		RequestContext ctx = RequestContext.getCurrentContext()	;
//		ctx.getResponse().addHeader("correlation-id",
//				tracer.getCurrentSpan().traceIdString()); 		
		eslogger.info("Correlation_id is "  +  tracer.getCurrentSpan().traceIdString());
		
		
		return null;
	}
}