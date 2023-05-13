package ua.AvadaMedia.adminREST.Model;

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
    @Column(nullable = false)
    private String path_to_logo_image;
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

    public String getAbout_cinema() {
        return about_cinema;
    }

    public String getConditions() {
        return conditions;
    }

    public String getPath_to_logo_image() {
        return path_to_logo_image;
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

    public void setAbout_cinema(String about_cinema) {
        this.about_cinema = about_cinema;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public void setPath_to_logo_image(String path_to_logo_image) {
        this.path_to_logo_image = path_to_logo_image;
    }

    public void setPath_to_top_banner_image(String path_to_top_banner_image) {
        this.path_to_top_banner_image = path_to_top_banner_image;
    }

    public void setSeo_block(SEOBlock seo_block) {
        this.seo_block = seo_block;
    }

}
