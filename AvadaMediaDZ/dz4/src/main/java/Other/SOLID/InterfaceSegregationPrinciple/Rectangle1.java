package Other.SOLID.InterfaceSegregationPrinciple;

public class Rectangle1 implements IHeight, IWidth, IArea {

    private int width;
    private int height;

    public Rectangle1(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getArea() {
        return width * height;
    }

}
// look in LiskovSubstitutionPrinciple package