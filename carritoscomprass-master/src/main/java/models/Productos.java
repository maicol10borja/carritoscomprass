package models;

public class Productos {
    private Long idProducto;  // Identificador único del producto
    private String nombre;    // Nombre del producto
    private String categoria; // Categoría del producto (por ejemplo, "Electrónica", "Ropa")
    private double precio;    // Precio del producto

    // Constructor vacío, utilizado cuando se necesita crear un objeto sin inicializar los campos
    public Productos() {
    }

    // Constructor con parámetros para inicializar un producto con todos los atributos
    public Productos(Long idProducto, String nombre, String categoria, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    // Getter para obtener el ID del producto
    public Long getIdProducto() {
        return idProducto;
    }

    // Setter para establecer el ID del producto
    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    // Getter para obtener el nombre del producto
    public String getNombre() {
        return nombre;
    }

    // Setter para establecer el nombre del producto
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para obtener la categoría del producto
    public String getCategoria() {
        return categoria;
    }

    // Setter para establecer la categoría del producto
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Getter para obtener el precio del producto
    public double getPrecio() {
        return precio;
    }

    // Setter para establecer el precio del producto
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

