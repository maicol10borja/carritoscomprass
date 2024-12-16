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
import java.io.PrintWriter;
import java.util.Optional;
@WebServlet({"/login", "/login.html"})  // Define las URL de acceso para este servlet, que maneja solicitudes a "/login" o "/login.html"
public class LoginServlet extends HttpServlet {

    // Credenciales de usuario y contraseña para el login
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Sección comentada que inicialmente estaba destinada a manejar cookies
        /* Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];

        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                // Convertimos de cookie a string
                .map(Cookie::getValue)
                .findAny(); */

        // Implementación de la autenticación mediante la sesión
        LoginService auth = new LoginServiceSessionImplement();

        // Creamos una variable Optional donde guardamos el nombre del usuario, obteniéndolo del método getUsername
        Optional<String> usernameOptional = auth.getUsername(req);

        // Si el nombre de usuario está presente en la sesión, muestra la información de login exitoso
        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = resp.getWriter()) {
                // Genera una página HTML que da la bienvenida al usuario que ya ha iniciado sesión anteriormente
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Hola " + usernameOptional.get() + "</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hola " + usernameOptional.get() + " ya has iniciado sesión anteriormente</h1>");
                out.println("<p><a href='" + req.getContextPath() + "/index.html'>Volver al inicio</a></p>");
                out.println("<p><a href='" + req.getContextPath() + "/logout'>Cerrar sesión</a></p>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            // Si no hay sesión activa, redirige al usuario al formulario de login
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtiene los parámetros del formulario de login (usuario y contraseña)
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Verifica si el usuario y la contraseña coinciden con los valores predefinidos
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {

            // Crea una nueva sesión HTTP y guarda el nombre de usuario en ella
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            // Redirige al usuario al formulario de login (quizás para mostrar un mensaje de éxito)
            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            // Si las credenciales son incorrectas, envía un error HTTP 401 (No autorizado)
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos, no está autorizado para ingresar a esta página!");
        }
    }
}

