// LoginServlet.java
package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ToDoDAO;
import dao.ToDoDAOImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L; // Fixes serialization warning

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    ServletContext ctxt = getServletContext();
    HttpSession session = request.getSession();
    
    String email = request.getParameter("email").trim();
    String pass = request.getParameter("pass").trim();

    if (email.isEmpty() || pass.isEmpty()) {
      request.setAttribute("loginError", "Email/Password cannot be empty.");
      ctxt.getRequestDispatcher("/Login.jsp").forward(request, response);
    } else {
      ToDoDAO dao = ToDoDAOImpl.getInstance();
      int regId = dao.login(email, pass);
      
      if (regId > 0) {
        session.setAttribute("regId", regId);
        ctxt.getRequestDispatcher("/ViewTasks.jsp").forward(request, response);
      } else {
        request.setAttribute("loginError", "Invalid Email or Password.");
        ctxt.getRequestDispatcher("/Login.jsp").forward(request, response);
      }
    }
  }
}