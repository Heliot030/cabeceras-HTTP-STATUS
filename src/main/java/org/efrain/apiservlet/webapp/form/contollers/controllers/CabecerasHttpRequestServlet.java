package org.efrain.apiservlet.webapp.form.contollers.controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras-request")
public class CabecerasHttpRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        String metodoHttp = req.getMethod();
        String requestUri = req.getRequestURI(); //da unaurl mas corta menos info
        String requestUrl = req.getRequestURL().toString();
        String contextPath = req.getContextPath();
        String servletPath = req.getServletPath();
        String ipCliente = req.getRemoteAddr();
        String ip = req.getRemoteAddr();
        int port = req.getLocalPort();
        String scheme = req.getScheme();
        String host = req.getHeader("Host");
        String url =scheme +"://"+ host +contextPath+ servletPath;

        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cabeceras HTTTP Request </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cabeceras HTTTP Request</h1>");
            out.println("<ul>");
            out.println("<li>HTTP : " + metodoHttp + "</li>");
            out.println("<li>requestUri : " + requestUri + "</li>");
            out.println("<li>requestUrl : " + requestUrl + "</li>");
            out.println("<li>contextPath : " + contextPath + "</li>");
            out.println("<li>servletPath : " + servletPath + "</li>");
            out.println("<li>ip local: " + ip + "</li>");
            out.println("<li>port : " + port + "</li>");
            out.println("<li>scheme : " + scheme + "</li>");
            out.println("<li>host : " + host + "</li>");
            out.println("<li>url : " + url + "</li>");
            out.println("<li>ip Client: " + ipCliente + "</li>");

             Enumeration<String> headerNmaes = req.getHeaderNames();
            while (headerNmaes.hasMoreElements()) {
                String cabecera = headerNmaes.nextElement();
                out.println("<li>" + cabecera + ": " + req.getHeader(cabecera) + "</li>");
            }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
