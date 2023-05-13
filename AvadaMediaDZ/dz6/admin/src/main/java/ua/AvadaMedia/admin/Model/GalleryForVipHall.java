package ua.AvadaMedia.admin.Model;

import javax.persistence.*;

@Entity
@Table(name = "gallery_for_vip_hall")
public class GalleryForVipHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String path_to_image;
    @ManyToOne(optional = false)
    private VipHall vip_hall;
    
}
