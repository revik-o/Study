package ua.AvadaMedia.admin.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket_booking")
public class TicketBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(optional = false)
    private User user;
    @ManyToOne(optional = false)
    private TakeUpPlaceList take_up_place_list;
    @ManyToOne(optional = false)
    private HallPlace hall_place;
    @Column(nullable = false)
    private Date date_;
}
