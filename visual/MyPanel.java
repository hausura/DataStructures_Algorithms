/**
 * Define a custom panel class that extends the JPanel class
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class MyPanel extends JPanel {
    public static Vector<Node> vectorNode = new Vector<>();
    private int lastX, lastY;

    public MyPanel() {
        this.setBounds(200, 0, 800, 700);
        this.setBackground(new Color(203, 237, 213));
        // set bounds and background color as before
        // add mouse listeners to the panel
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // record the mouse coordinates when it's pressed
                lastX = e.getX();
                lastY = e.getY();
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // calculate the new location of the panel based on the mouse movement
                int newX = MyPanel.this.getX() + e.getX() - lastX;
                int newY = MyPanel.this.getY() + e.getY() - lastY;
                // set the lower bound limit for the x,y coordinate
                newX = Math.min(newX, 0);
                newY = Math.min(newY, 0);
                MyPanel.this.setLocation(newX, newY);
            }
        });
    }

    // Override the paintComponent method to draw the components on the panel
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, getWidth(), getHeight());
        g2d.setColor(new Color(203, 237, 213));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(new Color(138, 182, 214));
        g2d.fillOval(870, 10, 50, 50);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("cosmic", Font.BOLD, 20));
        g2d.drawString("root", 875, 40);
        g2d.drawImage(new ImageIcon("components/images/40583670-d6478c9e-61c5-11e8-9551-6b55eacc7b8d.png").getImage(), 1130, 392, this);
        for (int i = 0; i < vectorNode.size(); i++) {
            // Delete a Node object if its c value is '1'
            if (vectorNode.get(i).c == '1') {
                vectorNode.get(i).delete(g);
                if (i > 0 && vectorNode.get(i).y > vectorNode.get(i - 1).y) {
                    vectorNode.get(i).deleteLine(g2d, vectorNode.get(i - 1));
                }
                // Draw a line from the node to the root if its y coordinate is 100
                if (vectorNode.get(i).y == 100) {
                    g2d.setStroke(new BasicStroke(2));
                    g2d.setColor(new Color(203, 237, 213));
                    g2d.drawLine(vectorNode.get(i).x + 25, vectorNode.get(i).y, 895, 60);
                }
            } else {
                for (Node node : vectorNode) {
                    if (node.x == vectorNode.get(i).x && node.y == vectorNode.get(i).y) {
                        if (vectorNode.get(i).isEndOfWord) {
                            if (vectorNode.get(i).isDel) {
                                node.isEndOfWord = false;
                                node.isDel = true;
                            } else {
                                node.isEndOfWord = true;
                            }
                        }
                    }
                    if (i == vectorNode.size() - 1) {
                        vectorNode.get(i).paint(g2d);
                    } else {
                        vectorNode.get(i).draw(g2d);
                    }
                    if (i > 0 && vectorNode.get(i).y > vectorNode.get(i - 1).y) {
                        vectorNode.get(i).drawLine(g2d, vectorNode.get(i - 1));
                    }
                    if (vectorNode.get(i).y == 100) {
                        g2d.setStroke(new BasicStroke(2));
                        g2d.setColor(new Color(204, 204, 255));
                        g2d.drawLine(vectorNode.get(i).x + 25, vectorNode.get(i).y, 895, 60);
                    }
                }
            }
        }
    }

    // delete the word indicator of a node object in the vectorNode array
    public static void deleteWord(int x, int y) {
        for (Node node : vectorNode) {
            if (node.x == x && node.y == y) {
                node.isEndOfWord = false;
            }
        }
    }

    // add a new method to get the preferred size of the panel
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(5000, 5000);
    }

}
