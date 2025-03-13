<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style>
  /* Centering the table */
  .center-table {
    margin: auto;
    width: 17%;
    border-collapse: collapse;
  }

  /* Centering text */
  .center-text {
    text-align: center;
  }

  /* Styling the error message */
  .error-msg {
    color: red;
    background-color: yellow;
    font-style: italic;
    text-align: center;
    padding: 5px;
  }
</style>
</head>
<body>

  <p class="error-msg">
    <% Object error = request.getAttribute("loginError"); %>
    <%=(error == null) ? "" : error.toString()%>
  </p>

  <form method="post" action="./LoginServlet">
    <table class="center-table" border="1">
      <tr>
        <th>Email</th>
        <td><input type="text" name="email" value=""></td>
      </tr>
      <tr>
        <th>Pass</th>
        <td><input type="password" name="pass" value=""></td>
      </tr>
      <tr>
        <th><input type="submit" name="submit" value="Login"></th>
        <td><input type="reset" name="reset" value="Clear"></td>
      </tr>
    </table>
  </form>

  <p class="center-text">New User, <a href="Register.html">SignUp</a></p>

</body>
</html>