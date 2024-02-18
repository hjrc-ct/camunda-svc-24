package com.bpm.security.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/** Responsible for handling header attributes in api http response. */
@Configuration
@Slf4j
public class ResFilter implements Filter {

  public static final String clientOriginURL = "http://localhost:4200";

  public ResFilter() {}

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    HttpServletRequest request = (HttpServletRequest) req;
    response.setHeader("Access-Control-Allow-Origin", clientOriginURL);
    response.setHeader("Access-Control-Allow-Methods", "HEAD, POST, GET, OPTIONS, DELETE, PUT");
    response.setHeader("Access-Control-Max-Age", "2400");
    response.setHeader("Access-Control-Allow-Headers", "*");

    if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
      log.info("Handle options method");
      response.setStatus(HttpServletResponse.SC_OK);
    } else {
      log.info("Handle non-options method -> " + request.getMethod());
      chain.doFilter(req, res);
    }
  }

  @Override
  public void init(FilterConfig filterConfig) {}

  @Override
  public void destroy() {}
}
