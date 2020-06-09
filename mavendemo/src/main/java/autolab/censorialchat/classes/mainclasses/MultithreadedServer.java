package autolab.censorialchat.classes.c10;

import java.util.List;

public class MultithreadedServer extends Thread {
    private Server server = new Server();

    public MultithreadedServer() {
    }

    public Server getServer() {
        return server;
    }

    @Override
    public void run() {
        //server.startServer();
        try {
            server.listen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Server.Connection> getConnections() {
        return this.server.getConnections();
    }
}
