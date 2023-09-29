import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class CurlyBraceDrawingExample extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int startX = 50;
        int startY = 50;
        int width = 40;
        int height = 80;

        // Create a GeneralPath for the curly brace
        GeneralPath brace = new GeneralPath();
        brace.moveTo(startX, startY);
        brace.lineTo(startX, startY + height);
        brace.curveTo(startX, startY + height, startX + width / 2, startY + height - 20, startX + width, startY + height);
        brace.lineTo(startX + width, startY);
        brace.curveTo(startX + width, startY, startX + width / 2, startY + 20, startX, startY);

        // Set the stroke for the brace
        g2d.setStroke(new BasicStroke(2));

        // Draw the curly brace
        g2d.draw(brace);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Curly Brace Drawing Example");
            CurlyBraceDrawingExample curlyBraceDrawingExample = new CurlyBraceDrawingExample();
            frame.add(curlyBraceDrawingExample);
            frame.setSize(200, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
