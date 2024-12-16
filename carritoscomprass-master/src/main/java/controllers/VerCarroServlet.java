package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ver-carro")  // Define la URL de acceso para este servlet, que maneja solicitudes a "/ver-carro"
public class VerCarroServlet extends HttpServlet {

    @Override
    // Método que maneja la solicitud GET, redirigiendo al usuario al JSP para ver el carrito
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Redirige la solicitud al archivo "carro.jsp" para mostrar el contenido del carrito
        // forward() transfiere la solicitud y respuesta al JSP sin cambiar la URL en el navegador
        getServletContext().getRequestDispatcher("/carro.jsp").forward(req, resp);
    }
}
