package Other.SOLID.LiskovSubstitutionPrinciple;

public class Square implements IShape {

    int sizeSide;

    public Square(int sizeSide) {
        this.sizeSide = sizeSide;
    }

    @Override
    public void setWidth(int width) {
        this.sizeSide = width;
    }

    @Override
    public void setHeight(int height) {
        this.sizeSide = height;
    }

    @Override
    public int getArea() {
        return sizeSide * sizeSide;
    }
}
