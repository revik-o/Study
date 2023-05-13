package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;

@Entity
@Table(name = "hall")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String number;
    private String description;
    @Column(nullable = false)
    private String path_to_layout_hall_image;
    private String path_to_top_banner_image;
    @ManyToOne(optional = false)
    private Cinema cinema;
    @ManyToOne
    private SEOBlock seo_block;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath_to_layout_hall_image() {
        return path_to_layout_hall_image;
    }

    public void setPath_to_layout_hall_image(String path_to_layout_hall_image) {
        this.path_to_layout_hall_image = path_to_layout_hall_image;
    }

    public String getPath_to_top_banner_image() {
        return path_to_top_banner_image;
    }

    public void setPath_to_top_banner_image(String path_to_top_banner_image) {
        this.path_to_top_banner_image = path_to_top_banner_image;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public SEOBlock getSeo_block() {
        return seo_block;
    }

    public void setSeo_block(SEOBlock seo_block) {
        this.seo_block = seo_block;
    }

}
