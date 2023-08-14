package com.clab.securecoding.auth.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class CrossSiteScriptingFilter extends GenericFilterBean {

    private static final String[] XSS_WHITELIST = {
            "/resources/files/**"
    };

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        boolean isWhitelisted = Arrays.stream(XSS_WHITELIST)
                .anyMatch(path -> antPathMatcher.match(path, requestURI));

        if( isWhitelisted ){
            chain.doFilter(request, response);
        } else {
            chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
        }
    }
}