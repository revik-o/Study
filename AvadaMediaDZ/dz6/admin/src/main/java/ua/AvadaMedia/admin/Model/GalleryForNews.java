package ua.AvadaMedia.admin.Model;

import javax.persistence.*;

@Entity
@Table(name = "gallery_for_news")
public class GalleryForNews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String path_to_image;
    @ManyToOne(optional = false)
    private News news;
    
}
