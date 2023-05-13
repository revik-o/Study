package ViewModel;

import ua.avadamedia.dz4.ModelDAO.BrandModelDAO;

import java.awt.Panel;
import java.awt.Container;
import java.awt.Label;
import java.util.ArrayList;

public class Observer {

    private BrandModelDAO data;
    private static ArrayList<Container> containers = new ArrayList<>();

    public Observer(){}

    public void setData(BrandModelDAO data) {
        this.data = data;
        notifyall();
    }

    public void addContainer(Container container) {
        containers.add(container);
    }

    public void removeContainer(Container container) {
        containers.remove(container);
    }

    int x = 0;
    int y = 0;

    public void notifyall() {
        containers.forEach(container -> {
            data.getAllData().forEach(brandModel -> {
                Label label = new Label();
                label.setLocation(x, y);
                y += 20;
                label.setText(brandModel.getBrandName() + " " + brandModel.getBrandLifetime());
                container.add(label);
            });
        });
    }

}
