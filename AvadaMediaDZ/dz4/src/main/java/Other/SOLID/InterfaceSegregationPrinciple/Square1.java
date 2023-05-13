package Other.SOLID.InterfaceSegregationPrinciple;

public class Square1 implements IWidth, IArea {

    int width;

    public Square1(int sizeSide) {
        this.width = sizeSide;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getArea() {
        return width * width;
    }
}
