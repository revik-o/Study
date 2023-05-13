package ua.AvadaMedia.adminREST.RequestBody.SEOBlockDependencies.CinemaDependencies;

public class CafeBarRequestBody {

    private String title;
    private String description;
    private boolean state = true;
    private long cinemaId = -1;
    private long seoBlockId = -1;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public long getSeoBlockId() {
        return seoBlockId;
    }

    public void setSeoBlockId(long seoBlockId) {
        this.seoBlockId = seoBlockId;
    }

}