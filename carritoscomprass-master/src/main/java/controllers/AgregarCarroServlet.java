package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Carro;
import models.ItemCarro;
import models.Productos;
import service.ProductoService;
import service.ProductoServiceImplement;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/agregar-carro")  // Define el servlet y su URL de acceso
public class AgregarCarroServlet extends HttpServlet {

    // Método que maneja solicitudes GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Obtiene el parámetro "idProducto" de la solicitud (el ID del producto a agregar al carrito)
        Long idProducto = Long.parseLong(req.getParameter("idProducto"));

        // Crea una instancia del servicio para manejar productos
        ProductoService service = new ProductoServiceImplement();

        // Busca el producto por ID, usando un Optional para manejar el caso de no encontrarlo
        Optional<Productos> producto = service.agregarPorId(idProducto);

        // Si el producto existe, lo agrega al carrito
        if (producto.isPresent()) {

            // Crea un nuevo ItemCarro, asignando cantidad 1 y el producto encontrado
            ItemCarro item = new ItemCarro(1, producto.get());

            // Obtiene la sesión actual
            HttpSession session = req.getSession();

            // Declara el objeto carro que representa el carrito de compras
            Carro carro;

            // Si no existe un carrito en la sesión, crea uno nuevo y lo guarda en la sesión
            if (session.getAttribute("carro") == null) {
                carro = new Carro();  // Crea un nuevo carrito vacío
                session.setAttribute("carro", carro);  // Guarda el carrito en la sesión
            } else {
                // Si ya existe un carrito, lo obtiene de la sesión
                carro = (Carro) session.getAttribute("carro");
            }

            // Agrega el item al carrito
            carro.addItemCarro(item);
        }

        // Redirige al usuario a la página donde se visualiza el carrito de compras
        resp.sendRedirect(req.getContextPath() + "/ver-carro");
    }
}

