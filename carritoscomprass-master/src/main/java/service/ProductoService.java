package service;

import models.Productos;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Productos> listar();
    //implementamos el metodo para añadir
    Optional<Productos> agregarPorId(Long idProducto);
}
