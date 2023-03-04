package by.application.productcatalog.model.dto;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
public class ProductDto {

    private Integer id;
    private Integer categoryId;
    private String name;
    private String info;
    private String price;
    private Integer count;
    private String manufacturer;
    private String releaseDate;
    private String link;
    //private byte[] photo;

    public ProductDto(Integer id, Integer categoryId, String name, String info,
                      String price, Integer count, String manufacturer, String releaseDate, String link) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.info = info;
        this.price = price;
        this.count = count;
        this.manufacturer = manufacturer;
        this.releaseDate = releaseDate;
        this.link = link;
       // this.photo = photo;
    }

}
