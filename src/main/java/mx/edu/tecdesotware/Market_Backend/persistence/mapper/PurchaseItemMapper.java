package mx.edu.tecdesotware.Market_Backend.persistence.mapper;

import mx.edu.tecdesotware.Market_Backend.domain.PurchaseItem;
import mx.edu.tecdesotware.Market_Backend.persistence.entity.ComprasProductos;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProductos producto);
    List<PurchaseItem> toPurchaseItems(List<ComprasProductos> productos);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),   // se asigna en el repositorio (setCompra)
            @Mapping(target = "producto", ignore = true)  // asociación de solo lectura → evita ciclos
    })
    ComprasProductos toComprasProductos(PurchaseItem purchaseItem);
}