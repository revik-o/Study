package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;

//@Deprecated
@Entity
@Table(name = "gallery_for_movie")
public class GalleryForMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String path_to_image;
    @ManyToOne(optional = false)
    private Movie movie;

    public long getId() {
        return id;
    }

    public String getPath_to_image() {
        return path_to_image;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPath_to_image(String path_to_image) {
        this.path_to_image = path_to_image;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

}
