package by.application.productcatalog.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "info")
    private String info;

    @Column(name = "price")
    private String price;

    @Column(name = "count")
    private Integer count;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "releaseDate")
    private String releaseDate;

    @Column(name = "link")
    private String link;

   // @Lob
   // @Column(name = "photo")
   // private byte[] photo;

    public Product(){}

    public Product(String name, String info, String price,
                   Integer count, String manufacturer, String releaseDate, String link) {

        this.name = name;
        this.info = info;
        this.price = price;
        this.count = count;
        this.manufacturer = manufacturer;
        this.releaseDate = releaseDate;
        this.link = link;
        //  this.photo = photo;
    }

    public Product(Integer id, Integer categoryId, String name, String info, String price,
                   Integer count, String manufacturer, String releaseDate, String link) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.info = info;
        this.price = price;
        this.count = count;
        this.manufacturer = manufacturer;
        this.releaseDate = releaseDate;
        this.link = link;
      //  this.photo = photo;
    }
}
