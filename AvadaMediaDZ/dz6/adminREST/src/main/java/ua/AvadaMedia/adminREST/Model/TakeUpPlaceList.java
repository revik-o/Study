package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;

@Entity
@Table(name = "take_up_place_list")
public class TakeUpPlaceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(optional = false)
    private ShowingMovie showingMovie;
    @ManyToOne(optional = false)
    private Hall hall;

}