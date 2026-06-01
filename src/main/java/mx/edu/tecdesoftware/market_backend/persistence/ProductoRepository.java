package mx.edu.tecdesoftware.market_backend.persistence;

import mx.edu.tecdesoftware.market_backend.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    //SELECT * FROM productos
    public List<Producto> getAll(){
        //Se "castear" de Iterable a la lista
        return (List<Producto>) productoCrudRepository.findAll();
    }
}
