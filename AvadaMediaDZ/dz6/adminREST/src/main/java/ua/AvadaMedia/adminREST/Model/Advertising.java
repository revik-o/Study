package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;

@Entity
@Table(name = "advertising")
public class Advertising {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String description;
    private String path_to_main_image;
    private boolean state = true;
    @ManyToOne
    private SEOBlock seo_block;

}
