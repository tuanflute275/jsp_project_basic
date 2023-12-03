package controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserModel;


// cấu hình route cho web như sau
@WebServlet(urlPatterns = {"/admin-home"})
// extends HttpServlet từ gói javax.servlet.http.HttpServlet đã import từ pom.xml
public class HomeController extends HttpServlet{
	// tự build của httpservlet nó phán đoán sau này đoạn code này có khả năng bị lỗi 
	// nên cần generate ra đoạn code này
	private static final long serialVersionUID = -7052979862106099394L;
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// khời tạo model user và set giá trị cho full name
				UserModel userModel = new UserModel();
				userModel.setFullName("tuanflutee");
				
				// set setAttribute với tên là model và có giá trị là userModel
				request.setAttribute("model", userModel);
				//RequestDispatcher để dùng phương thức getRequestDispatcher lấy ra view
				RequestDispatcher rd = request.getRequestDispatcher("/views/admin/home.jsp");
				// rd.forward trả về kết quả là view vừa định nghĩa ở trên
				rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
