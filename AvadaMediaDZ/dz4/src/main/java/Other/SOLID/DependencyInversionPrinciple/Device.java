package Other.SOLID.DependencyInversionPrinciple;

public class Device {

    IBrand brand;

    public String modelDevice;

    public Device(String modelDevice, IBrand brand) {
        this.modelDevice = modelDevice;
        this.brand = brand;
        brand.doSmth();
    }



    public void showInfo() {
        System.out.println(modelDevice + " : " + brand.getName());
    }

}