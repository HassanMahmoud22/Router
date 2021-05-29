import java.util.LinkedList;
import java.util.Queue;

public class Semaphore {
    private Queue<Device> devices;
    private int availableConnections;

    Semaphore() {
        availableConnections = 0;
        devices = null;
    }

    Semaphore(int availableConnections) {
        this.availableConnections = availableConnections;
        devices = new LinkedList<Device>();
    }

    public synchronized void connect(Device device) {
        device.router.printMessage(device.name + " (" + device.type + ") is trying to connect.");
        availableConnections--;
        if (availableConnections < 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        device.router.connect(device);

    }

    public synchronized void disconnect(Device device) {
        device.router.disconnect(device);
        availableConnections++;
        if (availableConnections <= 0) notify();
    }
}
