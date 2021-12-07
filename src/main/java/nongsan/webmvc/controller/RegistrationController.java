package nongsan.webmvc.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nongsan.webmvc.service.IUserService;

public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject
	IUserService userService;

	public RegistrationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/client/register.jsp");
    	dispatcher.forward(request, response);
    }
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String created = request.getParameter("created");

		if (userService.createUser(name, email, phone, username, password, created))
		{
			request.setAttribute("Message", "Bạn đã tạo tài khoàn thành công. Mời bạn đăng nhập <a href='/do-an-java/view/client/login'>tại đây!</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/view/client/register.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("errMessage", "Tạo tài khoản thất bại. Hãy thử lại !!!");
			RequestDispatcher rd = request.getRequestDispatcher("/view/client/register.jsp");
			rd.forward(request, response);
		}
	}
}
