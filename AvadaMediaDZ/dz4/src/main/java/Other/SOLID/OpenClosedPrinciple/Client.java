package Other.SOLID.OpenClosedPrinciple;

public class Client implements IClient {

    private String name;
    private IServer server;

    public Client(IServer server, String nameClient) {
        this.server = server;
        this.name = nameClient;
    }

    @Override
    public void connect() {
        server.acceptClient(this.name);
    }

}

class c {

    String name;
    Server server;

    public c(Server server) {
        this.server = new Server();
    }

    public void connect() {
        server.acceptClient(this.name);
    }

}