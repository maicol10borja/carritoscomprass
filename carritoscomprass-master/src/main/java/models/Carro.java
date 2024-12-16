package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    // Guarda todos los productos que el usuario va seleccionando para el carrito
    private List<ItemCarro> items;

    // Constructor que inicializa la lista de items del carrito
    public Carro() {
        this.items = new ArrayList<>();
    }

    // Método para agregar un ItemCarro al carrito
    public void addItemCarro(ItemCarro itemCarro) {
        // Si el producto ya está en el carrito, se aumenta la cantidad
        if (items.contains(itemCarro)) {
            // Busca el item específico en la lista utilizando un stream
            Optional<ItemCarro> optionalItemCarro = items.stream()
                    .filter(i -> i.equals(itemCarro)) // Compara si el producto ya está en el carrito
                    .findAny();  // Obtiene el item si existe

            if (optionalItemCarro.isPresent()) {
                // Si el item existe, se obtiene y se incrementa la cantidad
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad() + 1);  // Incrementa la cantidad de ese producto
            }
        } else {
            // Si el item no está en el carrito, lo agrega con cantidad 1
            this.items.add(itemCarro);
        }
    }

    // Método que devuelve la lista de todos los items en el carrito
    public List<ItemCarro> getItems() {
        return items;
    }

    // Método que calcula el total del carrito sumando los subtotales de cada item
    public double getTotal() {
        return items.stream()  // Itera sobre todos los items del carrito
                .mapToDouble(ItemCarro::getSbtotal)  // Obtiene el subtotal de cada item
                .sum();  // Suma los subtotales de todos los productos
    }
}
