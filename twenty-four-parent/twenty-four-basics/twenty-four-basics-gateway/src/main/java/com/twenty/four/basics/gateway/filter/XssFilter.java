package com.twenty.four.basics.gateway.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * @description: 防xss攻击
 * @author: chendong
 * @create: 2020/11/25 16:18
 */
@Component
@WebFilter
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        com.twenty.four.basics.gateway.filter.XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new  com.twenty.four.basics.gateway.filter.XssHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(xssHttpServletRequestWrapper, response);
    }


    @Override
    public void destroy() {

    }

}