package org.efrain.apiservlet.webapp.form.contollers.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.efrain.apiservlet.webapp.form.contollers.models.Producto;
import org.efrain.apiservlet.webapp.form.contollers.services.ProductoService;
import org.efrain.apiservlet.webapp.form.contollers.services.ProductosServiceImpl;

import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;

@WebServlet({"/productos.xls" ,"/productos.html"})
public class ProductoXlsServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, 
            IOException {
        ProductoService service = new ProductosServiceImpl();
        List<Producto> productos = service.Listar();

        resp.setContentType("text/html; charset=UTF-8");
        String servletPath = req.getServletPath();
        boolean esxls = servletPath.endsWith(".xls");
        if (esxls) {
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("Content-disposition",
                    "attachment; filename=productos.xls");
        }
        try (PrintWriter out = resp.getWriter()) {
            if (!esxls) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Listado de Productos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listado de Productos</h1>");
            out.println("<p><a href=\"" + req.getContextPath() +
                    "/productos.xls" + "\">exportar a xls </a></p>");
            }
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            out.println("<th>precio</th>");
            out.println("</tr>");

           productos.forEach(p -> {
               out.println("<tr>");
               out.println("<td>"+ p.getId() + "</td>");
               out.println("<td>"+ p.getNombre() + "</td>");
               out.println("<td>"+ p.getTipo() + "</td>");
               out.println("<td>"+ p.getPrecio() + "</td>");
               out.println("</tr>");
           });
            if (!esxls) {
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
            }
        }
    }
}
