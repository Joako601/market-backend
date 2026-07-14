package mx.edu.tecdesotware.Market_Backend.persistence.crud;

import mx.edu.tecdesotware.Market_Backend.persistence.entity.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {

    // Query Method: SELECT * FROM compras WHERE id_cliente = ?
    List<Compra> findByIdCliente(String idCliente);
}