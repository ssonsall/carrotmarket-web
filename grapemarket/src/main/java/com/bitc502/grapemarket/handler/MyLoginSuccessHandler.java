package com.bitc502.grapemarket.handler;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class MyLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
			if(request.getHeader("DeviceType") != null && request.getHeader("DeviceType").equals("android")) {
				System.out.println("안드로이드 접속_myhandler");
				request.setAttribute("testSession", request.getSession().getId());
				RequestDispatcher dis = request.getRequestDispatcher("/android/loginSuccess");
				dis.forward(request, response);
				//response.sendRedirect("/android/loginSuccess");
			}else {
				System.out.println("Web 접속");
				response.sendRedirect("/");
			}
	}

}
