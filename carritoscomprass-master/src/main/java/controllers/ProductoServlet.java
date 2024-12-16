package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Productos;
import service.LoginService;
import service.LoginServiceSessionImplement;
import service.ProductoService;
import service.ProductoServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
@WebServlet("/productos")  // Define la URL de acceso para este servlet, que maneja solicitudes a "/productos"
public class ProductoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Se crea una instancia del servicio que maneja la lógica de productos
        ProductoService servicios = new ProductoServiceImplement();

        // Obtiene la lista de productos desde el servicio
        List<Productos> productos = servicios.listar();

        // Se crea una instancia del servicio de autenticación para obtener el nombre de usuario de la sesión
        LoginService auth = new LoginServiceSessionImplement();

        // Obtiene el nombre de usuario desde la sesión (si está presente)
        Optional<String> usernameOptional = auth.getUsername(req);

        // Establece el tipo de contenido de la respuesta como HTML con codificación UTF-8
        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            // Inicia la plantilla HTML que se enviará al navegador
            out.print("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Hola Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listado de productos</h1>");

            // Si el usuario está autenticado (nombre de usuario presente en la sesión), muestra un mensaje de bienvenida
            if (usernameOptional.isPresent()){
                out.println("<div style='color:blue;'> Hola "+ usernameOptional.get() +" Bienvenido</div>");
            }

            // Crea una tabla para mostrar la lista de productos
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>ID PRODUCTO</th>");
            out.println("<th>NOMBRE</th>");
            out.println("<th>CATEGORIA</th>");

            // Si el usuario está autenticado, agrega las columnas de precio y agregar al carro
            if (usernameOptional.isPresent()){
                out.println("<th>PRECIO</th>");
                out.println("<th>AGREGAR</th>");
            }

            out.println("</tr>");

            // Itera sobre la lista de productos y genera una fila para cada uno
            productos.forEach(pr ->{
                out.println("<tr>");
                out.println("<td>"+ pr.getIdProducto()+"</td>");
                out.println("<td>"+ pr.getNombre()+"</td>");
                out.println("<td>"+ pr.getCategoria()+"</td>");

                // Si el usuario está autenticado, muestra el precio y un enlace para agregar el producto al carrito
                if(usernameOptional.isPresent()){
                    out.println("<td>"+ pr.getPrecio()+"</td>");
                    out.println("<td><a href=\""
                            + req.getContextPath()
                            +"/agregar-carro?idProducto="+ pr.getIdProducto()
                            +"\">Agregar carro</a></td>");
                }

                out.println("</tr>");
            });
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}

