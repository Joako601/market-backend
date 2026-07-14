package mx.edu.tecdesotware.Market_Backend.persistence;

import mx.edu.tecdesotware.Market_Backend.domain.Product;
import mx.edu.tecdesotware.Market_Backend.domain.repository.ProductRepository;
import mx.edu.tecdesotware.Market_Backend.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesotware.Market_Backend.persistence.entity.Producto;
import mx.edu.tecdesotware.Market_Backend.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository // Le dices a Spring que esta clase se comunicará en la BD

public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCrudRepository  productoCrudRepository;
    @Autowired
    private ProductMapper productMapper;

    // SELECT * FROM productos
    public List<Product> getAll(){
        //Se "Castea" de Iterable a la Lista
        List<Producto> productos =  (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    /*
        SELECT *
        FROM producto
        WHERE cantidad_stock < ? AND estado = TRUE
    */

    public Optional<List<Product>> getScarceProducts(int quantity){
        Optional <List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return Optional.of(productMapper.toProducts(productos.get()));
    }

    // Obtener un producto dado el id
    public Optional<Product> getProduct(int productId){
        return productoCrudRepository.findById(productId).map(producto -> productMapper.toProduct(producto));
    }

    // Guardar un producto
    public Product save(Product product){
        Producto producto = productMapper.toProducto(product);
        return productMapper.toProduct(productoCrudRepository.save(producto));
    }

    // Eliminar por id
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }

}

