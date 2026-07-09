package mx.edu.tecdesoftware.market_backend.persistence.mapper;

import mx.edu.tecdesoftware.market_backend.domain.PurchaseItem;
import mx.edu.tecdesoftware.market_backend.persistence.entity.CompraProducto;
import mx.edu.tecdesoftware.market_backend.persistence.entity.CompraProductoPK;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mapping(source = "id.idProducto", target = "productId")
    @Mapping(source = "cantidad", target = "quantity")
    @Mapping(source = "total", target = "total")
    @Mapping(source = "estado", target = "active")
    PurchaseItem toPurchaseItem(CompraProducto compraProducto);

    List<PurchaseItem> toPurchaseItems(List<CompraProducto> compraProductos);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "compra", ignore = true)
    @Mapping(target = "producto", ignore = true)
    CompraProducto toCompraProducto(PurchaseItem purchaseItem);

    List<CompraProducto> toCompraProductos(List<PurchaseItem> purchaseItems);

    @AfterMapping
    default void setId(PurchaseItem purchaseItem, @MappingTarget CompraProducto compraProducto) {
        CompraProductoPK pk = new CompraProductoPK();
        pk.setIdProducto(purchaseItem.getProductId());
        compraProducto.setId(pk);
    }
}