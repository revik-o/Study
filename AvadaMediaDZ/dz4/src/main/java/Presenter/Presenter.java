package Presenter;

import ua.avadamedia.dz4.Model.BrandModel;
import ua.avadamedia.dz4.ModelDAO.BrandModelDAO;

import java.awt.*;
import java.util.UUID;

public class Presenter {

    private BrandModelDAO brandModelDAO = new BrandModelDAO();

    int x = 0;
    int y = 0;

    public void getData(Container pane) {
        brandModelDAO.addNewBrand(new BrandModel(UUID.randomUUID(), "Asus", 12));
        brandModelDAO.addNewBrand(new BrandModel(UUID.randomUUID(), "Apple", 30));
        brandModelDAO.addNewBrand(new BrandModel(UUID.randomUUID(), "Lenovo", 15));
        brandModelDAO.getAllData().forEach(brandModel -> {
            Label label = new Label();
            label.setLocation(x, y);
            y += 20;
            label.setText(brandModel.getBrandName() + " " + brandModel.getBrandLifetime());
            pane.add(label);
        });
        y = 0;
        x += 20;
    }

}
