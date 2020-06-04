package autolab.censorialchat.classes.c10;

import java.util.List;

public class MultithreadedServer extends Thread {
    private Server server = new Server();

    public MultithreadedServer() {
    }

    @Override
    public void run() {
        server.startServer();
    }

    public List<Server.Connection> getConnections() {
        return this.server.getConnections();
    }
}
