package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String path_to_main_image;
    private String path_to_top_banner_image;
    @ManyToOne
    private SEOBlock seo_block;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPath_to_main_image() {
        return path_to_main_image;
    }

    public String getPath_to_top_banner_image() {
        return path_to_top_banner_image;
    }

    public SEOBlock getSeo_block() {
        return seo_block;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPath_to_main_image(String path_to_main_image) {
        this.path_to_main_image = path_to_main_image;
    }

    public void setPath_to_top_banner_image(String path_to_top_banner_image) {
        this.path_to_top_banner_image = path_to_top_banner_image;
    }

    public void setSeo_block(SEOBlock seo_block) {
        this.seo_block = seo_block;
    }

}