package models;

import java.util.Objects;

public class ItemCarro {
    private int cantidad;       // Cantidad del producto en el carrito
    private Productos productos; // Producto específico que está en el carrito

    // Constructor que inicializa la cantidad y el producto en el carrito
    public ItemCarro(int cantidad, Productos productos) {
        this.cantidad = cantidad;
        this.productos = productos;
    }

    // Getter para la cantidad del producto en el carrito
    public int getCantidad() {
        return cantidad;
    }

    // Setter para actualizar la cantidad del producto en el carrito
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Getter para obtener el producto asociado a este item del carrito
    public Productos getProductos() {
        return productos;
    }

    // Setter para actualizar el producto de este item del carrito
    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    // Override del método equals para comparar dos objetos ItemCarro
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si ambos objetos son iguales, retorna true
        if (o == null || getClass() != o.getClass()) return false; // Si son de diferente clase o uno es nulo, retorna false
        ItemCarro itemCarro = (ItemCarro) o; // Cast del objeto a ItemCarro
        // Compara los productos en base a su ID y nombre
        return Objects.equals(productos.getIdProducto(), itemCarro.productos.getIdProducto())
                && Objects.equals(productos.getNombre(), itemCarro.productos.getNombre());
    }

    // Método que calcula el subtotal de este item del carrito
    public double getSbtotal() {
        return cantidad * productos.getPrecio(); // Multiplica la cantidad por el precio del producto
    }
}