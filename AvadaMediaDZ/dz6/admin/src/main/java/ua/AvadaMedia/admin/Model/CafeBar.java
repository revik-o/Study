package ua.AvadaMedia.admin.Model;

import javax.persistence.*;

@Entity
@Table(name = "cafe_bar")
public class CafeBar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String path_to_main_image;
    private boolean state = true;
    @ManyToOne(optional = false)
    @JoinColumn(name = "cinema")
    private Cinema cinema;
    @ManyToOne
    @JoinColumn(name = "seo_block")
    private SEOBlock seo_block;

}