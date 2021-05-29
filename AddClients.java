import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddClients {
    Router router;
    private JTextField textField1;
    private JTextField textField2;
    private JButton runButton;
    private JButton addDeviceButton;
    private JPanel panel;

    AddClients(Router router, JFrame currentFrame) {
        this.router = router;
        ArrayList<Device> devices = new ArrayList<>();
        addDeviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deviceName = textField1.getText();
                String deviceType = textField2.getText();
                Device newDevice = new Device(deviceName, deviceType, router);
                devices.add(newDevice);
                textField1.setText("");
                textField2.setText("");
            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFrame.setContentPane(new Log(currentFrame).getPanel());
                currentFrame.pack();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                for (int i = 0; i < devices.size(); i++) {
                    devices.get(i).start();
                }
            }
        });

    }

    public JPanel getPanel() {
        return this.panel;
    }
}
