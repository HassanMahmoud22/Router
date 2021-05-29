import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Network {
    public static void main(String[] args) {

        Router router = new Router();
        int maxConnections, numOfDevices;
        ArrayList<Device> devices = new ArrayList<>();
        Scanner intInput = new Scanner(System.in);
        Scanner stringInput = new Scanner(System.in);

        // Take router input
        System.out.print("How many devices can connect to the router at once?: ");
        maxConnections = intInput.nextInt();
        if (maxConnections < 1) {
            while (maxConnections < 1) {
                System.out.println("Please enter a positive number greater than 0: ");
                maxConnections = intInput.nextInt();
            }
        } else {
            router = new Router(maxConnections);
        }

        // Take devices input
        System.out.print("How many devices would you like to add?: ");
        numOfDevices = intInput.nextInt();
        if (numOfDevices < 1) {
            while (numOfDevices < 1) {
                System.out.print("Please enter a positive number greater than 0: ");
                numOfDevices = intInput.nextInt();
            }
        } else {
            for (int i = 0; i < numOfDevices; i++) {
                System.out.print("Enter Device " + (i + 1) + " Name: ");
                String newName = stringInput.nextLine();
                System.out.print("Enter Device " + (i + 1) + " Type: ");
                String newType = stringInput.nextLine();
                Device newDevice = new Device(newName, newType, router);
                devices.add(newDevice);
            }
        }

        // Start queueing devices to connect to router
        for (int i = 0; i < devices.size(); i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            devices.get(i).start();
        }
    }
}
