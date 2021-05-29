import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Log {
    private JButton exitButton;
    private JPanel panel;
    private JTextArea textArea1;

    Log(JFrame currentFrame) {
        class CustomOutputStream extends OutputStream {
            @Override
            public void write(int b) throws IOException {
                textArea1.setText(textArea1.getText() + String.valueOf((char) b));
            }
        }
        System.setOut(new PrintStream(new CustomOutputStream()));

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
