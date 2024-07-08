package com.platzicurso.market.persistence.crud;

import com.platzicurso.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

// Parámetros de CrudRepository: <Entidad, Tipo de dato de la PK>
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    // También podemos hacerlo con una query normal
    // @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)s
    List<Producto>              findByIdCategoriaOrderByNombreAsc(Integer idCategoria);

    Optional<List<Producto>>    findByCantidadStockLessThanAndEstado(Integer cantidadStock, Boolean estado);
}
