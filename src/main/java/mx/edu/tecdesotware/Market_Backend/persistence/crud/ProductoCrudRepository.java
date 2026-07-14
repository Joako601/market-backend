package mx.edu.tecdesotware.Market_Backend.persistence.crud;

import mx.edu.tecdesotware.Market_Backend.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //Query method
    /*
     SELECT *
     FROM categorias
     WHERE id = ?
     ORDER BY nombre DESC

     Obtener la lista de productos por id de categoria ordenados ascendentemente
    */

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidad, boolean estado);



}
