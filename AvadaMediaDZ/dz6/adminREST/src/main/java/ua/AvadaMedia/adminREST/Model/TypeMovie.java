package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;

@Entity
@Table(name = "type_movie")
public class TypeMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private boolean three_d;
    @Column(nullable = false)
    private boolean two_d;
    @Column(nullable = false)
    private boolean imax;

}
