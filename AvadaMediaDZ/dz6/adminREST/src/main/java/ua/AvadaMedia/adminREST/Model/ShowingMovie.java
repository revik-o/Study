package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "showing_movie")
public class ShowingMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(optional = false)
    private MoviePage movie_page;
    @Column(nullable = false)
    private Date start_date;
    @Column(nullable = false)
    private Date end_date;

}
