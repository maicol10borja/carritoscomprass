package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Declaramos y inicializamos los parámetros para la conexión a la base de datos
    private static String url = "jdbc:mysql://localhost:3306/carrito?useTimezone=true&serverTimezone=UTC";
    private static String username = "root";
    private static String password = "";

    // Implementamos un método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // Método que verifica la conexión y muestra un mensaje de éxito
    public static void verificarConexion() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Conexión exitosa.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    // Método principal para probar la conexión
    public static void main(String[] args) {
        verificarConexion();
    }
}

