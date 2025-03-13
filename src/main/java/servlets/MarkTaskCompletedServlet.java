// MarkTaskCompletedServlet.java
package servlets;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ToDoDAO;
import dao.ToDoDAOImpl;

@WebServlet("/MarkTaskCompletedServlet")
public class MarkTaskCompletedServlet extends HttpServlet {
  private static final long serialVersionUID = 1L; // Fixes serialization warning

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    ServletContext context = getServletContext();
    
    try {
      int regId = Integer.parseInt(request.getParameter("regId"));
      int taskId = Integer.parseInt(request.getParameter("taskId"));
      
      ToDoDAO dao = ToDoDAOImpl.getInstance();
      boolean flag = dao.markTaskCompleted(taskId, regId);
      
      if (flag) {
        context.getRequestDispatcher("/ViewTasks.jsp").forward(request, response);
      } else {
        response.getWriter().println("Transaction Failed: Task not marked as completed.");
      }
    } catch (NumberFormatException e) {
      response.getWriter().println("Invalid input: regId and taskId must be integers.");
    }
  }
}