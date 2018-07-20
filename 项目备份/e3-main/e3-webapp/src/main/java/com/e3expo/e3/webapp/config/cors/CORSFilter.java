package com.e3expo.e3.webapp.config.cors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

	private String corsAddress;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest)request;
//		System.out.println("-------------------------------");
//		System.out.println(httpRequest.getHeader("Origin"));
//		System.out.println("-------------------------------------");
		String url = httpRequest.getHeader("Origin");
		httpResponse.setHeader("Access-Control-Allow-Origin", url);
		httpResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET,PUT,DELETE,POST,OPTIONS");
		httpResponse.setHeader("Access-Control-Allow-Credentials","true");
        filterChain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public String getCorsAddress() {
		return corsAddress;
	}

	public void setCorsAddress(String corsAddress) {
		this.corsAddress = corsAddress;
	}


}
