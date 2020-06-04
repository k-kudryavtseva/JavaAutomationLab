package autolab;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class ServerTest extends BaseTest {

    @Test(threadPoolSize = POOL_SIZE, invocationCount = 1, invocationTimeOut = 10000)
    public void testConnection() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(server);
            assertFalse(server.getConnections().isEmpty(), "Empty connections");
            assertEquals(server.getConnections().size(), POOL_SIZE, "Wrong Connections count");
        }
    }
}
