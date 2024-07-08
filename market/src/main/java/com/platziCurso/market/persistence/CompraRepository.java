package com.platzicurso.market.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.platzicurso.market.domain.Purchase;
import com.platzicurso.market.domain.repository.PurchaseRepository;
import com.platzicurso.market.persistence.crud.CompraCrudRepository;
import com.platzicurso.market.persistence.mapper.PurchaseMapper;
import com.platzicurso.market.persistence.entity.Compra;

@Repository // Anotación que indica que es un componente de persistencia de Spring y que se encarga de interactuar con la base de datos
public class CompraRepository implements PurchaseRepository {
    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private PurchaseMapper mapper;

    // Nota: Recordemos que el CRUD es lo que nos permite hacer magia con queries de SQL sin tener que escribir SQL

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>)compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
            .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        compra.getProductos().forEach(producto -> producto.setCompra(compra)); // Compra ya conoce sus productos, le decimos a los productos a qué compra pertenecen
        // Para guardar los productos en cascada tenemos que estar SEGUROS de que la compra conoce sus productos y que los productos conocen su compra

        /* 
         * Hubo que hacer una cosa extrañísima acá. Ver video 29
         ! Determinar que los datos se guardan en cascada en Compras
         ! Por una relación de circularidad que francamente no sé lo que es tuve que agregar un MapsId en la relación de CompraProducto
        */

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
    
    
}
