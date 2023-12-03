package controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.NewModel;
import model.UserModel;
import service.ICategoryService;
import service.INewService;
import service.IUserService;
import ultils.FormUtil;
import ultils.SessionUtil;
import java.util.ResourceBundle;


// cấu hình route cho web như sau
@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat"})
// extends HttpServlet từ gói javax.servlet.http.HttpServlet đã import từ pom.xml
public class HomeController extends HttpServlet{
	
	@Inject
	private ICategoryService categoryService;
	
	@Inject
	private INewService newService;
	
	@Inject
	private IUserService userService;
	
	// tự build của httpservlet nó phán đoán sau này đoạn code này có khả năng bị lỗi 
	// nên cần generate ra đoạn code này
	private static final long serialVersionUID = -7052979862106099394L;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action != null && action.equals("login")) {
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");
			if(message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		}else if(action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath()+"/trang-chu");
		}else {
			request.setAttribute("categories", categoryService.findAll());
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, request);
			model = userService.login(model.getUserName(), model.getPassword(), 1);
			if(model != null) {
				SessionUtil.getInstance().putValue(request, "USERMODEL", model);
				if(model.getRole().getCode().equals("USER")) {
					response.sendRedirect(request.getContextPath()+"/trang-chu");
				}else if(model.getRole().getCode().equals("ADMIN")) {
					response.sendRedirect(request.getContextPath()+"/admin-home");
				}
			}else {
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
			}
		}
	}

}
