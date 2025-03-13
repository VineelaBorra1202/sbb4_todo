// LogoutServlet.java
package servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
  private static final long serialVersionUID = -3214055023475045142L; // Fixes serialization warning

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    HttpSession session = request.getSession();
    ServletContext context = getServletContext();
    
    session.invalidate(); // Invalidate session to logout user
    context.getRequestDispatcher("/Login.jsp").forward(request, response);
  }
}