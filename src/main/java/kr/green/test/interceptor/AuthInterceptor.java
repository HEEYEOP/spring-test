package kr.green.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest r, 
			HttpServletResponse response, 
			Object handler)
			throws Exception {
		
		Object user = r.getSession().getAttribute("user");
		
		if(user == null) {
			response.sendRedirect(r.getContextPath()+"/"); // 리턴타입을 "/"이걸로 설정하여라 => 즉, 메인화면(로그인)으로 보내라
		}
		return true;
	}

}
