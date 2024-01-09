package com.example.springapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.example.springapp.service.UserDetailsServiceImpl;
import com.example.springapp.util.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class CustomFilter implements Filter {

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("In Filter");
        HttpServletRequest hReq = (HttpServletRequest) req;

        String header = hReq.getHeader("Authorization");

        if (!validateHeader(header)) {
            chain.doFilter(req, res);
            return;
        }

        String jwtToken = header.substring(7);
        if (!jwtUtil.validateAccessToken(jwtToken)) {
            System.out.println("Invalid Jwt Token: " + jwtToken);
            chain.doFilter(req, res);
            return;
        }

        System.out.println("Jwt in filter " + jwtToken);

        setAuthenticationContext(hReq, jwtToken);

        chain.doFilter(req, res);
    }

    private boolean validateHeader(String header) {
        if (header == null || (!header.startsWith("Bearer "))) {
            System.out.println("In Filter: invalid header");
            return false;
        }
        return true;
    }

    private void setAuthenticationContext(HttpServletRequest hReq, String jwtToken) {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("In if setAuthenticationContext");

            String email = jwtUtil.getUsername(jwtToken);

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(hReq));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}

