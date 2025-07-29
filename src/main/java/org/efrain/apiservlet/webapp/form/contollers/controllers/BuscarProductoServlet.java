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
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/buscar-producto")
public class BuscarProductoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductosServiceImpl();
        String nombre = req.getParameter("producto");
        Optional <Producto> encontrado = service.Listar().stream().filter(p -> {
            if( nombre == null || nombre.isBlank()){ //son validaciones de login
                return false;
            }
            return p.getNombre().equals(nombre);
                }).findFirst();
        if( encontrado.isPresent()){
            resp.setContentType("text/html; charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Porudcto encontrado </title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Porudcto encontrado </h1>");
                out.println("<h3>Porudcto encontrado"+ encontrado.get().getNombre() +" </h3>");
                out.println("<h3>Porudcto Precio "+ encontrado.get().getPrecio() +" </h3>");
                out.println("</body>");
                out.println("</html>");
            }
        }else{
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "lo sentimos no fue encontrado ");
        }
    }
}
