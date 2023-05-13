package ua.avadamedia.dz4.Model;

import java.util.UUID;

public class BrandModel {

    private UUID id;
    private String brandName;
    private int brandLifetime;

    public BrandModel(UUID id, String brandName, int brandLifetime) {
        this.id = id;
        this.brandName = brandName;
        this.brandLifetime = brandLifetime;
    }

    public UUID getId() {
        return id;
    }

    public int getBrandLifetime() {
        return brandLifetime;
    }

    public String getBrandName() {
        return brandName;
    }

}