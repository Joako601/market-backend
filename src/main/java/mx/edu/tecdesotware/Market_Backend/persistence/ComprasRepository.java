package mx.edu.tecdesotware.Market_Backend.persistence;

import mx.edu.tecdesotware.Market_Backend.domain.Purchase;
import mx.edu.tecdesotware.Market_Backend.domain.repository.PurchaseRepository;
import mx.edu.tecdesotware.Market_Backend.persistence.crud.CompraCrudRepository;
import mx.edu.tecdesotware.Market_Backend.persistence.entity.Compra;
import mx.edu.tecdesotware.Market_Backend.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ComprasRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return Optional.of(mapper.toPurchases(compraCrudRepository.findByIdCliente(clientId)));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);

        // PASO CRÍTICO: cada producto debe apuntar a la compra padre
        // para que @MapsId derive el id_compra y la FK no falle.
        compra.getComprasProductos().forEach(producto -> producto.setCompra(compra));

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}