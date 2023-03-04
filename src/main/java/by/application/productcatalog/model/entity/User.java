package by.application.productcatalog.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private String contact;

    @Column(name = "access_mode")
    private Integer accessMode;

    public User(){}

    public User(Integer id, Integer typeId, String name, String login, String password,
                String email, String contact, Integer accessMode) {
        this.id = id;
        this.typeId = typeId;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.contact = contact;
        this.accessMode = accessMode;
    }
}
