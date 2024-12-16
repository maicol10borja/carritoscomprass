package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.LoginService;
import service.LoginServiceSessionImplement;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")  // Define la URL de acceso para este servlet, que maneja solicitudes a "/logout"
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Se crea una instancia del servicio de autenticación, que en este caso utiliza sesión
        LoginService auth = new LoginServiceSessionImplement();

        // Se obtiene el nombre de usuario desde la sesión (si está presente)
        Optional<String> usernameOptional = auth.getUsername(req);

        // Si el nombre de usuario está presente, es decir, si el usuario está autenticado
        if (usernameOptional.isPresent()) {
            HttpSession session = req.getSession();  // Obtiene la sesión actual del usuario

            // Invalida la sesión, eliminando cualquier atributo almacenado en ella (como el nombre de usuario)
            session.invalidate();
        }

        // Redirige al usuario a la página de login después de cerrar sesión
        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
