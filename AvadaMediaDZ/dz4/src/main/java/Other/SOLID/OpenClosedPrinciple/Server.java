package Other.SOLID.OpenClosedPrinciple;

public class Server implements IServer {

    @Override
    public void acceptClient(String clientName) {
        System.out.println("Accepted client: " + clientName);
    }

}
