package ua.AvadaMedia.adminREST.RequestBody.SEOBlockDependencies;

public class MovieRequestBody {

    private String name;
    private String description;
//    private String link;
//    private boolean twoD;
//    private boolean threeD;
//    private boolean imax;
    private long seoBlockId = -1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSeoBlockId() {
        return seoBlockId;
    }

    public void setSeoBlockId(long seoBlockId) {
        this.seoBlockId = seoBlockId;
    }

}