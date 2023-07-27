/**
 * This class creates the operation buttons used in the GUI.
 */

import javax.swing.*;
import java.awt.*;

public class OperationButton {
    JButton insert = new JButton(), search = new JButton(), delete = new JButton(), LCP = new JButton(), clear = new JButton();
    JButton[] buttons = {insert, search, delete, LCP, clear};

    public OperationButton() {
        createButton();
    }

    // set the properties of the operation buttons.
    public void createButton() {
        insert.setText("Insert");
        search.setText("Search");
        delete.setText("Delete");
        LCP.setText("Longest Prefix");
        clear.setText("Clear");

        int X_Label, Y_Label = 75;
        int WIDTH_Label, HEIGHT_Label = 40;

        for (JButton button : buttons) {
            button.setFont(new Font("cosmic", Font.BOLD, 20));
            button.setMargin(new Insets(0, 0, 0, 0));
            if (button == LCP) {
                X_Label = 20;
                WIDTH_Label = 150;
            } else {
                X_Label = 50;
                WIDTH_Label = 70;
            }
            button.setBounds(X_Label, Y_Label, WIDTH_Label, HEIGHT_Label);
            button.setBackground(new Color(135, 203, 185));
            button.setForeground(new Color(31, 70, 144));
            button.setFocusable(false);
            button.setBorder(BorderFactory.createLineBorder(new Color(135, 203, 185)));
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Y_Label += 60;
        }
        insert.setToolTipText("Insert a new word");
        search.setToolTipText("Search for a word");
        delete.setToolTipText("Delete a word");
        LCP.setToolTipText("Find the longest common prefix");
        clear.setToolTipText("Clear the trie");
    }
}
