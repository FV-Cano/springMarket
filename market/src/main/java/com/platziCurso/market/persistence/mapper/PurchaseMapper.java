package com.platzicurso.market.persistence.mapper;

import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.platzicurso.market.persistence.entity.Compra;
import com.platzicurso.market.domain.Purchase;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class}) // Internamente mappeamos dentro de la compra todos sus productos
public interface PurchaseMapper {
    @Mappings({
        @Mapping(source = "idCompra", target = "purchaseId"),
        @Mapping(source = "idCliente", target = "clientId"),
        @Mapping(source = "fecha", target = "date"),
        @Mapping(source = "medioPago", target = "paymentMethod"),
        @Mapping(source = "comentario", target = "comment"),
        @Mapping(source = "estado", target = "state"),
        @Mapping(source = "productos", target = "items") // Este mapping en particular es el que usa el PurchaseItemMapper
    })
    Purchase toPurchase(Compra compra);
    // No necesitamos hacer un mapper para List<Purchase> porque ya sabe convertir compras en purchase gracias a la anotación de arriba
    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target = "cliente", ignore = true)
    })
    Compra toCompra(Purchase purchase);
}
