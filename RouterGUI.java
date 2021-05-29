import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RouterGUI {
    private JPanel panel;
    private JTextField textField1;
    private JButton continueButton;
    private JButton exitButton;

    Router router;

    RouterGUI(JFrame currentFrame) {

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int maxConnections = Integer.parseInt(textField1.getText());
                    currentFrame.setContentPane(new AddClients(new Router(maxConnections), currentFrame).getPanel());
                    currentFrame.pack();
                } catch (NumberFormatException ex) {
                    textField1.setText("Enter a valid integer");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        textField1.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                textField1.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CISCO Router 2020");
        frame.setContentPane(new RouterGUI(frame).panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
