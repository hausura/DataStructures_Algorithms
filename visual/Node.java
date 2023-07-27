/**
 * This class represents a node that can be drawn on a graphics object
 */

import java.awt.*;

public class Node {
    protected int x; // x-coordinate of the node
    protected int y; // y-coordinate of the node
    protected char c; // character to be displayed in the node
    protected boolean isEndOfWord; // boolean indicating whether this node represents the end of a word
    protected boolean isDel; // boolean indicating whether this node should be deleted

    public Node(int x, int y, char c, boolean isEndOfWord, boolean isDel) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.isEndOfWord = isEndOfWord;
        this.isDel = isDel;
    }

    // draw the node on a given graphics object
    public void draw(Graphics g) {
        if (isEndOfWord && !isDel) {
            g.setColor(new Color(154, 205, 50)); // Green color for highlighting
        } else {
            g.setColor(new Color(100, 149, 237)); // Blue color for non-word nodes
        }
        g.fillOval(x, y, 40, 40);
        g.setColor(Color.BLACK);
        g.setFont(new Font("cosmic", Font.BOLD, 20));
        g.drawString(String.valueOf(c), x + 15, y + 26);
    }


    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHints(rh);
        g2d.setColor(Color.yellow);
        g2d.fillOval(x, y, 40, 40);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("cosmic", Font.BOLD, 20));
        g2d.drawString(String.valueOf(c), x + 15, y + 26);
    }

    // draw a line between this node and another given node on a given graphics object
    public void drawLine(Graphics g, Node node) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(204, 204, 255));
        g2d.drawLine(this.x + 20, this.y, node.x + 20, node.y + 40);
    }

    // delete the node by painting over it
    public void delete(Graphics g) {
        g.setColor(new Color(203, 237, 213));
        g.fillOval(x, y, 40, 40);
    }

    // delete the line between this node and another given node by painting over it
    public void deleteLine(Graphics g, Node node) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(203, 237, 213));
        g2d.drawLine(this.x + 20, this.y, node.x + 20, node.y + 40);
    }
}
