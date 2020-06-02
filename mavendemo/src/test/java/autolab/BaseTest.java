package autolab;

import autolab.censorialchat.classes.c10.Client;
import autolab.censorialchat.classes.c10.MultithreadedServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseTest {

    protected final int POOL_SIZE = 10;

    protected MultithreadedServer server;
    protected ExecutorService clientPool;

    @BeforeClass
    public void allocateResources(){

        server = new MultithreadedServer();
        server.start();

        clientPool = Executors.newFixedThreadPool(POOL_SIZE);
        Runnable clientInitTask = () -> new Client().run();
        for (int i = 0; i < POOL_SIZE; i++){
            System.out.println(i);
            clientPool.execute(clientInitTask);
        }
        System.out.println(clientPool);
    }

    @AfterClass
    public void shutdownResources(){
        clientPool.shutdown();
    }

}