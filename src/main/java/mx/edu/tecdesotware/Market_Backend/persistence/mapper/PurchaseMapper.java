package mx.edu.tecdesotware.Market_Backend.persistence.mapper;

import mx.edu.tecdesotware.Market_Backend.domain.Purchase;
import mx.edu.tecdesotware.Market_Backend.persistence.entity.Compra;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "comprasProductos", target = "items")
    })
    Purchase toPurchase(Compra compra);
    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)  // relación inversa → evita ciclo Compra ↔ Cliente
    Compra toCompra(Purchase purchase);
}