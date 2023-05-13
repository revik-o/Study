package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;

@Entity
@Table(name = "hall_place")
public class HallPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private int row_;
    @Column(nullable = false)
    private int number_of_place;
    @ManyToOne(optional = false)
    private Hall hall;

}
