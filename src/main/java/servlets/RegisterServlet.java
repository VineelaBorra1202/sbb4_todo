// RegisterServlet.java
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Register;
import dao.ToDoDAO;
import dao.ToDoDAOImpl;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

  private static final long serialVersionUID = 1L; // Fixes serialVersionUID warning

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    try {
      // Reading form data
      String fname = request.getParameter("fname").trim();
      String lname = request.getParameter("lname").trim();
      String email = request.getParameter("email").trim();
      String pass = request.getParameter("pass").trim();
      long mobile = Long.parseLong(request.getParameter("mobile").trim());
      String address = request.getParameter("address").trim();
      
      // Store form data in Register bean object
      Register reg = new Register(0, fname, lname, email, pass, mobile, address);
      
      // Create DAO object
      ToDoDAO dao = ToDoDAOImpl.getInstance();
      int regId = dao.register(reg);
      
      // Redirect based on registration success
      if (regId > 0) {
        response.sendRedirect("./Login.jsp");
      } else {
        response.sendRedirect("./Register.html");
      }
    } catch (Exception e) {
      e.printStackTrace();
      response.sendRedirect("./error.jsp"); // Redirect to an error page if something goes wrong
    }
  }
}