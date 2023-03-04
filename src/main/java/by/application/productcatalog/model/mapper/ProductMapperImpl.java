package by.application.productcatalog.model.mapper;

import by.application.productcatalog.model.dto.ProductDto;
import by.application.productcatalog.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toProductDto(Product product) {
        if(product == null) {
            return null;
        }

        return ProductDto.builder()
                .id(product.getId())
                .categoryId(product.getCategoryId())
                .name(product.getName())
                .info(product.getInfo())
                .price(product.getPrice())
                .count(product.getCount())
                .manufacturer(product.getManufacturer())
                .releaseDate(product.getReleaseDate())
                .link(product.getLink())
               // .photo(product.getPhoto())
                .build();
    }

    @Override
    public Product toProduct(ProductDto productDto) {
        if(productDto == null) {
            return null;
        }

        return Product.builder()
                .id(productDto.getId())
                .categoryId(productDto.getCategoryId())
                .name(productDto.getName())
                .info(productDto.getInfo())
                .price(productDto.getPrice())
                .count(productDto.getCount())
                .manufacturer(productDto.getManufacturer())
                .releaseDate(productDto.getReleaseDate())
                .link(productDto.getLink())
              //  .photo(productDto.getPhoto())
                .build();
    }
}
