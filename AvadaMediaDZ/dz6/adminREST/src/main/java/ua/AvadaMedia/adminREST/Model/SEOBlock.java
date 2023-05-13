package ua.AvadaMedia.adminREST.Model;

import javax.persistence.*;

@Entity
@Table(name = "seo_block")
public class SEOBlock {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "seo_url")
    private String seo_url;
    @Column(name = "seo_title")
    private String seo_title;
    @Column(name = "seo_keywords")
    private String seo_keywords;
    @Column(name = "seo_description")
    private String seo_description;

    public long getId() {
        return id;
    }

    public String getSeo_url() {
        return seo_url;
    }

    public String getSeo_title() {
        return seo_title;
    }

    public String getSeo_keywords() {
        return seo_keywords;
    }

    public String getSeo_description() {
        return seo_description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSeo_url(String seo_url) {
        this.seo_url = seo_url;
    }

    public void setSeo_title(String seo_title) {
        this.seo_title = seo_title;
    }

    public void setSeo_keywords(String seo_keywords) {
        this.seo_keywords = seo_keywords;
    }

    public void setSeo_description(String seo_description) {
        this.seo_description = seo_description;
    }

}