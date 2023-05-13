package ViewModel;

import ua.avadamedia.dz4.Model.BrandModel;
import ua.avadamedia.dz4.ModelDAO.BrandModelDAO;

import java.util.UUID;

public class ViewModel {

    private BrandModelDAO brandModelDAO = new BrandModelDAO();

    int x = 0;
    int y = 0;

    public void getData() {
        brandModelDAO.addNewBrand(new BrandModel(UUID.randomUUID(), "Asus", 12));
        brandModelDAO.addNewBrand(new BrandModel(UUID.randomUUID(), "Apple", 30));
        brandModelDAO.addNewBrand(new BrandModel(UUID.randomUUID(), "Lenovo", 15));
        Observer observer = new Observer();
        observer.setData(brandModelDAO);
    }

}
