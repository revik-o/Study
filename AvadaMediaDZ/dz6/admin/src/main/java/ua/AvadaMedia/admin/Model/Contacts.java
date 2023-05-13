package ua.AvadaMedia.admin.Model;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String address;
    private String coordinate_for_map;
    private boolean state = true;
    @ManyToOne(optional = false)
    private Cinema cinema;
    @ManyToOne
    private SEOBlock seo_block;

}
