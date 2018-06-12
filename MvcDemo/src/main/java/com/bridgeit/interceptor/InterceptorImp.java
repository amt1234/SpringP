package com.bridgeit.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

public class InterceptorImp implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String dob = request.getParameter("dob");
		long mobileNo;
		
		if(request.getParameter("mobileNo")==null)
		{
			mobileNo=0;
		}else {
			mobileNo = Long.parseLong(request.getParameter("mobileNo"));
		}
			
			if ((!(username.equals("")))
					&& (!(email.equals("")) && (regexemail(email)))
					&& (!(password.equals("")) && (password.length() >6))
					&& ((((1111111111) * 7) < mobileNo && mobileNo > (9 * 1111111111))) && !(dob.equals(""))) {
				System.out.println("valid info");
				return true;
				
			}else 
			{
				ModelAndView modelAndView=new ModelAndView();
				modelAndView.setViewName("Register");
				modelAndView.addObject("message","please enter valid information");
				throw new ModelAndViewDefiningException(modelAndView);
			}
					
	}
		
	public boolean regexname(String username) {
		Pattern pattern = Pattern.compile("[a-zA-Z]*");
		Matcher matcher = pattern.matcher(username);
		if (matcher.find() && matcher.group().equals(username)) {
			return true;
		}
		return false;
	}

	public boolean regexemail(String email) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
		Matcher matcher = pattern.matcher(email);
		if (matcher.find() && matcher.group().equals(email)) {
			return true;
		}
		return false;
	}
}
