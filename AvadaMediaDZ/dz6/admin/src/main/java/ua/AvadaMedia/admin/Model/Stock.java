package ua.AvadaMedia.admin.Model;

import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String path_to_main_image;
    private String video_link;
    private boolean state = true;
    @ManyToOne(optional = false)
    private Cinema cinema;
    @ManyToOne
    private SEOBlock seo_block;

}