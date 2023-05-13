package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;

@Entity
@Table(name = "children_s_room")
public class Children_sRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String path_to_main_image;
    private boolean state = true;
    @ManyToOne(optional = false)
    private Cinema cinema;
    @ManyToOne
    private SEOBlock seo_block;

}
