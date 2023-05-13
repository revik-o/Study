package ua.AvadaMedia.admin.Model;

import javax.persistence.*;

@Entity
@Table(name = "cinema")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    private String description;
    private String about_cinema;
    private String conditions;
    private String path_to_logo_image;
    private String path_to_top_banner_image;
    @ManyToOne
    private SEOBlock seo_block;

}
