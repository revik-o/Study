package ua.avadamedia.dz4.ModelDAO;

import ua.avadamedia.dz4.Model.BrandModel;

import java.util.List;
import java.util.UUID;

public interface IBrandModelDAO {

    void addNewBrand(BrandModel brandModel);
    void removeNewBrand(BrandModel brandModel);
    BrandModel getBrandModel(UUID uuid);
    List<BrandModel> getAllData();

}
