package com.dissi.kafkaworkshop.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CORSFilter implements Filter {

  private final List<String> allowedOrigins = Arrays.asList("http://localhost:3000", "http://localhost",
    "https://kafka.dissi.me");

  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
    throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    HttpServletRequest request = (HttpServletRequest) req;
    String origin = request.getHeader("Origin");

    //Tweak here, pass your origin URL here
    response.setHeader("Access-Control-Allow-Origin", allowedOrigins.contains(origin) ? origin : "*");
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Allow-Methods", "UPGRADE, POST, GET, PUT, OPTIONS, DELETE");
    response.setHeader("Vary", "Origin");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    response.setHeader("Access-Control-Expose-Headers", "Location");

    //Just REPLY OK if request method is OPTIONS for CORS (pre-flight)
    if (request.getMethod().equals("OPTIONS")) {
      response.setHeader("Access-Control-Max-Age", "86400");
      response.setStatus(HttpServletResponse.SC_OK);
      return;
    }
    chain.doFilter(req, res);
  }
}
