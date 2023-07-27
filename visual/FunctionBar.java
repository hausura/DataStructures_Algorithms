/**
 * A JPanel that represents the function bar of the GUI.
 * The function bar contains a label, a text field, and a set of operation buttons.
 * It is used to display information and interact with the trie data structure.
 */

import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FunctionBar extends JPanel {
    JLabel label = new JLabel();
    JTextField textField = new JTextField(20);
    OperationButton operationButton = new OperationButton();


    public FunctionBar() {
        setBackground(new Color(135, 203, 185));
        setBounds(0, 0, 200, 700);
        setLayout(null);

        label.setBounds(6, 500, 200, 200);
        try {
            label.setFont(loadFont("components/font/CascadiaCodePL_0.ttf", 30));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        label.setForeground(new Color(57, 91, 100));
        label.setText("Visualizer");
        JLabel label1 = new JLabel();
        label1.setBounds(60, 450, 200, 200);
        try {
            label1.setFont(loadFont("components/font/CascadiaCodePL_0.ttf", 30));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        label1.setForeground(new Color(57, 91, 100));
        label1.setText("Trie");
        add(label);
        add(label1);

        // Create and add a text field for user input
        textField.setBorder(BorderFactory.createEmptyBorder());
        textField.setBounds(25, 35, 150, 25);
        try {
            textField.setFont(loadFont("components/font/CascadiaCodePL_0.ttf", 20));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        add(textField);

        // Create and add operation buttons for interacting with the trie
        for (int i = 0; i < operationButton.buttons.length; i++) {
            add(operationButton.buttons[i]);
        }


    }

    // Load a font from a file path and create a new Font object with a specified size.
    public Font loadFont(String fontFilePath, int size) throws IOException, FontFormatException {
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(fontFilePath))) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            return font.deriveFont(Font.BOLD, size);
        }
    }

}
