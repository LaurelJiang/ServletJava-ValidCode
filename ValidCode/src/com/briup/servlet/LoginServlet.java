package com.briup.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.briup.pojo.Users;
import com.briup.service.UsersService;
import com.briup.serviceimpl.UsersServiceImp;
/**
 *  
 * @author JT
 *
 */
@WebServlet("/login")
public class LoginServlet  extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UsersService usersService;
	
	@Override
	public void init() throws ServletException {
		ApplicationContext ac=WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		ac.getBean("usersService",UsersServiceImp.class);
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String code = req.getParameter("code");
		String codeSession = req.getSession().getAttribute("code").toString();
		
		System.out.println("-------------if code0------------");
		if(codeSession.equals(code)) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			Users users = new Users();
			users.setUsername(username);
			users.setPassword(password);
			Users user = usersService.login(users);
			System.out.println("---------if code1--------------");
			if(user!=null) {
				resp.sendRedirect("main.jsp");
			}else {
				System.out.println("---------if code2--------------");
				req.setAttribute("error", "�û������벻��ȷ");
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
		}
		else{
			System.out.println("---------if code3--------------");
			req.setAttribute("error", "��֤�벻��ȷ");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}
