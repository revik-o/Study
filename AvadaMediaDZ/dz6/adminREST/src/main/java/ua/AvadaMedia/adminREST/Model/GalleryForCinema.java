package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;

@Entity
@Table(name = "gallery_for_cinema")
public class GalleryForCinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String path_to_image;
    @ManyToOne(optional = false)
    private Cinema cinema;

    public long getId() {
        return id;
    }

    public String getPath_to_image() {
        return path_to_image;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPath_to_image(String path_to_image) {
        this.path_to_image = path_to_image;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

}