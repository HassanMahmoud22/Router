import java.util.Random;

public class Device extends Thread {
    String name;
    String type;
    Router router;

    Device() {
        this.name = "";
        this.type = "";
        this.router = null;
    }

    Device(String name, String type, Router router) {
        this.name = name;
        this.type = type;
        this.router = router;
    }

    public void doActivity() {
        router.printMessage("Connection " + router.lastConnection + ": " + name + " (" + type + ") is performing activity.");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        router.semaphore.connect(this);
        doActivity();
        router.semaphore.disconnect(this);
    }
}
