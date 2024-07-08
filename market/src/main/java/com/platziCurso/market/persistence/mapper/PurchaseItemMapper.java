package com.platzicurso.market.persistence.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.platzicurso.market.persistence.entity.ComprasProducto;
import com.platzicurso.market.domain.PurchaseItem;

@Mapper(componentModel = "spring", uses = {ProductMapper.class}) // Decimos que es un mapper y gracias al component model podemos inyectarlo en otros lugares
public interface PurchaseItemMapper {
    @Mappings({
        @Mapping(source = "id.idProducto", target = "productId"),
        @Mapping(source = "cantidad", target = "quantity"),
        @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);
    /*
     * @Mapping(source = "total", target = "total") // No es necesario porque se llaman igual
    */
    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target = "compra", ignore = true),
        @Mapping(target = "producto", ignore = true),
        @Mapping(target = "id.idCompra", ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem item);
    // Obtengo el error de que no estoy mappeando productos, compra e idCompra, lo cual es correcto, a diferencia de PurchaseItem, ComprasProducto tiene una llave compuesta, donde no estamos mappeando idCompra, y además tenemos las relaciones de compra y producto, que tampoco estamos mapeando. Estas relaciones no nos interesan, pero tenemos que mapearlas de todas formas para decir que las vamos a ignorar.
}
