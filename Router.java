import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;

public class Router {
    private int maxConnections;
    Semaphore semaphore;
    Device[] connections;
    int lastConnection;

    Router() {
        maxConnections = 0;
        semaphore = null;
        connections = null;
        lastConnection = 0;
    }

    Router(int maxConnections) {
        this.maxConnections = maxConnections;
        semaphore = new Semaphore(maxConnections);
        connections = new Device[maxConnections];
        lastConnection = 0;
    }

    public void printMessage(String message) {
        System.out.println(message);
        try {
            FileWriter writer = new FileWriter("output.txt", true);
            writer.write(message + '\n');
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(Device device) {
        for (int i = 0; i < maxConnections; i++) {
            if (connections[i] == null) {
                connections[i] = device;
                lastConnection = i + 1;
                break;
            }
        }
        printMessage("Connection " + lastConnection + ": " + device.name + " (" + device.type + ") connected");
    }

    public void disconnect(Device device) {
        for (int i = 0; i < connections.length; i++) {
            if (connections[i] != null && connections[i].name == device.name) {
                lastConnection = i + 1;
                connections[i] = null;
                break;
            }
        }
        printMessage("Connection " + lastConnection + ": " + device.name + " (" + device.type + ") disconnected.");
    }

}