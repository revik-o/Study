package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String nickname;
    private String e_mail;
    private String address;
    @Column(nullable = false)
    private String password; // TODO
    private String card_number;
    private String language;
    @ManyToOne(optional = false)
    private Gender gender;
    private String phone;
    private Date birthday;
    @ManyToOne(optional = false)
    private City city;

}
