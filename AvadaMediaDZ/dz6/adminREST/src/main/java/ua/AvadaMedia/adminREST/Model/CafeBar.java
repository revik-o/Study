package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;

@Entity
@Table(name = "cafe_bar")
public class CafeBar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath_to_main_image() {
        return path_to_main_image;
    }

    public void setPath_to_main_image(String path_to_main_image) {
        this.path_to_main_image = path_to_main_image;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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