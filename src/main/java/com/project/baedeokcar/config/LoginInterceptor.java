package com.project.baedeokcar.config;

import com.project.baedeokcar.domain.dto.member.MemberDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        MemberDto authInfo = (MemberDto) session.getAttribute("authInfo");
        if (authInfo != null) {
            return true;
        } else {
            response.sendRedirect("/login");
            return false;
        }
    }
}
