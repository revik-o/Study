package ua.AvadaMedia.adminREST.RequestBody.SEOBlockDependencies.CinemaDependencies;

public class ContactsRequestBody {

    private String address;
    private String coordinateForMap;
    private boolean state = true;
    private long cinemaId = -1;
    private long seoBlockId = -1;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinateForMap() {
        return coordinateForMap;
    }

    public void setCoordinateForMap(String coordinateForMap) {
        this.coordinateForMap = coordinateForMap;
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