package ua.AvadaMedia.admin.Model;

import javax.persistence.*;

@Entity
@Table(name = "vip_hall")
public class VipHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String path_to_main_image;
    private boolean state = true;
    @ManyToOne(optional = false)
    private Cinema cinema;
    @ManyToOne
    private SEOBlock seo_block;

}
