package org.efrain.apiservlet.webapp.form.contollers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.efrain.apiservlet.webapp.form.contollers.models.Producto;
import org.efrain.apiservlet.webapp.form.contollers.services.ProductoService;
import org.efrain.apiservlet.webapp.form.contollers.services.ProductosServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/productos.json")
public class ProductoJsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ProductoService service = new ProductosServiceImpl();
        List<Producto> productos = service.Listar();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(productos);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
              ServletInputStream jsonStream = req.getInputStream();
              ObjectMapper mapper = new ObjectMapper();
              //convrtimos un json a procuto clase
              Producto producto =  mapper.readValue(jsonStream , Producto.class);
        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Detalle Producto desde JSON </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Detalle Producto desde JSON </h1>");
            out.println("<ul>");
            out.println("<li> Id : " + producto.getId() + "</li>");
            out.println("<li> Nombre : " + producto.getNombre() + "</li>");
            out.println("<li> tipo : " + producto.getTipo() + "</li>");
            out.println("<li> precio  : " + producto.getPrecio() + "</li>");
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
