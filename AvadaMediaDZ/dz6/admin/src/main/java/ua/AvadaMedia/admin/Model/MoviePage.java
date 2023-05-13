package ua.AvadaMedia.admin.Model;

import javax.persistence.*;

@Entity
@Table(name = "movie_page")
public class MoviePage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String path_to_main_image;
    private String trailer_link;
    @ManyToOne(optional = false)
    private Movie movie;
    @ManyToOne(optional = false)
    private TypeMovie typeMovie;
    @ManyToOne
    private SEOBlock seo_block;

}
