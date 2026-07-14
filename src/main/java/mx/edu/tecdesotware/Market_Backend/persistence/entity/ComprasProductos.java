package mx.edu.tecdesotware.Market_Backend.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "compras_productos")

public class ComprasProductos {

    @EmbeddedId
    private CompraProductoPK id = new CompraProductoPK();

    private Integer cantidad;
    private Double total;
    private Boolean estado;

    //Saber todos los productos que hay en una compra.
    // @MapsId: el id_compra de la PK se deriva de esta compra (generada en cascada).
    @ManyToOne
    @MapsId("idCompra")
    @JoinColumn(name = "id_compra")
    private Compra compra;
    //Saber todos las compras que hay en un producto
    @ManyToOne
    @JoinColumn(name = "id_producto",  insertable = false, updatable = false)
    private Producto producto;


    public CompraProductoPK getId() {
        return id;
    }

    public void setId(CompraProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
