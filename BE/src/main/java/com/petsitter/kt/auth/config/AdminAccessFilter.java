package com.petsitter.kt.auth.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AdminAccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Object role = httpRequest.getSession().getAttribute("role");
        if (role == null || !"ADMIN".equals(role.toString())) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "관리자만 접근 가능합니다.");
            return;
        }

        chain.doFilter(request, response);
    }
}
