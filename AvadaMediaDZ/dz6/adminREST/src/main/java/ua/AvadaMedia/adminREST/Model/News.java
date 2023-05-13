package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private Date date_of_publication;
    @Column(nullable = false)
    private String path_to_main_image;
    private String video_link;
    private boolean state = true;
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

    public Date getDate_of_publication() {
        return date_of_publication;
    }

    public void setDate_of_publication(Date date_of_publication) {
        this.date_of_publication = date_of_publication;
    }

    public String getPath_to_main_image() {
        return path_to_main_image;
    }

    public void setPath_to_main_image(String path_to_main_image) {
        this.path_to_main_image = path_to_main_image;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
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
