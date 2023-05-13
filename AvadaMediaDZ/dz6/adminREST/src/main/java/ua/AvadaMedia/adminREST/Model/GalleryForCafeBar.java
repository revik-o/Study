package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;

@Entity
@Table(name = "gallery_for_cafe_bar")
public class GalleryForCafeBar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String path_to_image;
    @ManyToOne(optional = false)
    private CafeBar cafeBar;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath_to_image() {
        return path_to_image;
    }

    public void setPath_to_image(String path_to_image) {
        this.path_to_image = path_to_image;
    }

    public CafeBar getCafeBar() {
        return cafeBar;
    }

    public void setCafeBar(CafeBar cafeBar) {
        this.cafeBar = cafeBar;
    }

}