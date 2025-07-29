package org.efrain.apiservlet.webapp.form.contollers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "123456";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       String username = req.getParameter("username");
       String password = req.getParameter("password");
       if (username.equals(USERNAME) && password.equals(PASSWORD)) {
           req.getSession().setAttribute("username", username);
           resp.setContentType("text/html; charset=UTF-8");
           try (PrintWriter out = resp.getWriter()) {
               out.println("<html>");
               out.println("<head>");
               out.println("<title> login correcto </title>");
               out.println("</head>");
               out.println("<body>");
               out.println("<h1> login correcto </h1>");
               out.println("<h3>" + username +"Has inniciado secion </h3>");
               out.println("</body>");
               out.println("</html>");
           }
       }else {
           resp.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                   "no estas autorizado a entrar");
       }
    }
}
