package Other.SOLID.DependencyInversionPrinciple;

public class Brand implements IBrand {

    @Override
    public String getName() {
        return "some Brand";
    }

    @Override
    public void doSmth() {
        System.out.println("Device created");
    }
}
