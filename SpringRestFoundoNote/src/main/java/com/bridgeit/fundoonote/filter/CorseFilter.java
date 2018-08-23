package com.bridgeit.fundoonote.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

public class CorseFilter extends OncePerRequestFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(CorseFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		LOGGER.info("filter apply.....");
		response.addHeader("Access-Control-Allow-Origin", "*");

		if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
			LOGGER.info("Sending Header....");
			// CORS "pre-flight" request
			response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			//response.addHeader("Access-Control-Allow-Headers", "Authorization");
			response.setHeader("Access-Control-Allow-Headers", "content-type,userid");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Max-Age", "1");
		}

		filterChain.doFilter(request, response);
	}

}
