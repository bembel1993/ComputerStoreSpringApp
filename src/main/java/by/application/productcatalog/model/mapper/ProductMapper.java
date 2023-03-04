package by.application.productcatalog.model.mapper;

import by.application.productcatalog.model.dto.ProductDto;
import by.application.productcatalog.model.entity.Product;
import org.mapstruct.Mapper;

import javax.persistence.Column;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toProductDto(Product product);

    Product toProduct(ProductDto productDto);
}
