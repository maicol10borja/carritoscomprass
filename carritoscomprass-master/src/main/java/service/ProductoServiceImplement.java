package service;

import models.Productos;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImplement implements ProductoService{
    @Override
    public List<Productos> listar() {
        return Arrays.asList(new Productos(1L,"Laptop", "tecnología",523.25),
                new Productos(2L,"cocina", "hogar", 325.24),
                new Productos(3L,"mouse","tecnología",15.25));
    }

    @Override
    //busca el id del producto
    public Optional<Productos> agregarPorId(Long idProducto) {
        return listar().stream().filter(p -> p.getIdProducto().equals(idProducto)).findAny();
    }
}
