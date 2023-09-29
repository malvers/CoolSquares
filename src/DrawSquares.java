import com.sun.javafx.scene.paint.GradientUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class DrawSquares extends JButton implements KeyListener, MouseListener, MouseMotionListener {

    private JFrame myFrame = null;
    int x = 120;
    int a = 40;

    private int xStart = x;
    private int aStart = a;
    private int mouseStart = 0;
    private boolean drawAnnotation = true;
    private boolean drawAxis = true;
    private int size;

    private CurlyBrace cb = null;

    public DrawSquares(JFrame frame) {
        myFrame = frame;
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
        repaint();
    }

    public void paint(Graphics gIn) {

        Graphics2D g = (Graphics2D) gIn;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setFont(new Font("Arial", Font.PLAIN, 12));

        int grey = 160;

        int originX = getWidth() / 2;
        int originY = getHeight() / 2;

        int posX = originX;
        int posY = originY;

        /// draw center ///////////////////////////////
        g.setColor(Color.ORANGE);
        g.fillRect(posX, posY, a, a);

        /// draw first to the right of center ////////
        posX = originX + a;
        posY = (int) (originY - x);
        size = (int) (a + x);
        g.setColor(new Color(190, 215, 240));
        g.fillRect(posX, posY, size, size);
        g.setColor(new Color(grey, grey, grey));
        g.drawRect(posX, posY, size, size);

        /// draw first below center //////////////////
        posX = originX;
        posY = originY + a;
        size = (int) (2 * a + x);
        g.setColor(new Color(190, 215, 240));
        g.fillRect(posX, posY, size, size);
        g.setColor(new Color(grey, grey, grey));
        g.drawRect(posX, posY, size, size);

        /// draw lower left below center /////////////
        size = (int) (3 * a + x);
        posX = originX - size;
        posY = originY;
        g.setColor(new Color(190, 215, 240));
        g.fillRect(posX, posY, size, size);
        g.setColor(new Color(grey, grey, grey));
        g.drawRect(posX, posY, size, size);

        /// draw upper left below center /////////////
        posX = originX - size;
        posY = originY - size - a;
        size = (int) (4 * a + x);
        g.setColor(new Color(190, 215, 240));
        g.fillRect(posX, posY, size, size);
        g.setColor(new Color(grey, grey, grey));
        g.drawRect(posX, posY, size, size);

        /// draw green ///////////////////////////////
        posX = originX + a;
        posY = originY - size;
        size = 4 * a;
        g.setColor(new Color(146, 208, 80));
        g.fillRect(posX, posY, size, size);
        g.setColor(new Color(grey, grey, grey));
        g.drawRect(posX, posY, size, size);

        if (drawAnnotation) {
            drawAnnotations(g, originX, originY);
        }

        Point p1 = new Point(150, 100);

        cb = new CurlyBrace(new Point(100, 100), 200);

        cb.draw(g, true);

    }

    private void drawAnnotations(Graphics2D g, int originX, int originY) {

        int aa = a / 10;
        int val = aa * aa;
        String str = "";
        Rectangle2D b;

        /// draw x ///////////////////////////////////
        if (drawAxis) {

            g.setColor(new Color(160, 0, 0));
            g.setStroke(new BasicStroke(2));
            g.drawLine(originX + a, originY, originX + a, (int) (originY - x));
            g.drawString("x = " + x / 10, originX + a + 4, (int) (originY - x / 2 + 5));
        }

        /// draw y ///////////////////////////////////
        if (drawAxis) {
            g.setColor(new Color(0, 0, 80));
            g.setStroke(new BasicStroke(2));

            str = "y = " + aa * 4;
            b = g.getFontMetrics().getStringBounds(str, g).getBounds();
            g.drawLine(originX + a, (int) (originY - x), originX + a, (int) (originY - x - 4 * a));
            g.drawString(str, (int) (originX - b.getWidth() + a - 4), originY - x - (2 * a) + 4);
        }

        /// draw a area
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        str = "" + (aa * 4 * aa * 4);
        b = g.getFontMetrics().getStringBounds(str, g).getBounds();
        g.setColor(new Color(0, 0, 80));
        g.drawString(str, (int) (originX + a + a * 2 - b.getWidth() / 2), originY - x - (2 * a) + 4);

        /// draw a ///////////////////////////////////
        str = "a = " + a / 10;
        if (drawAxis) {
            g.setFont(new Font("Arial", Font.PLAIN, 12));
            g.setColor(new Color(0, 0, 80));
            g.setStroke(new BasicStroke(2));

            g.drawLine(originX + a, originY, originX + a, originY + a);
            g.drawString(str, originX + a + 4, originY + a / 2 + 4);
        }

        /// draw a area
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        str = "" + val;
        b = g.getFontMetrics().getStringBounds(str, g).getBounds();
        g.setColor(new Color(0, 0, 80));
        g.drawString("" + val, (int) (originX + (a / 2) - (b.getWidth() / 2.0)), originY + a / 2 + 6);

        g.setStroke(new BasicStroke(1));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        DrawSquares drawSquares = new DrawSquares(frame);
        frame.add(drawSquares);
        frame.setLocation(200, 400);
        frame.setSize(1200, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        drawSquares.setFrameTitle();
        drawSquares.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                x += 1;
                break;
            case KeyEvent.VK_DOWN:
                x -= 1;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_A:
                drawAnnotation = !drawAnnotation;
                break;
            case KeyEvent.VK_W:
                if (e.isControlDown() || e.isMetaDown()) {
                    System.exit(0);
                }
                break;
            case KeyEvent.VK_X:
                drawAxis = !drawAxis;
                break;
        }
        setFrameTitle();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        xStart = x;
        aStart = a;
        mouseStart = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        int delta = e.getY() - mouseStart;

        if (e.isShiftDown()) {
            a = (int) (aStart + delta);
            if (a < 14) {
                a = 14;
            }
            if (a > 80) {
                a = 80;
            }
        } else {
            x = xStart + delta;
            if (x < 0) {
                x = 0;
            }
            if (x > 200) {
                x = 200;
            }
        }
        setFrameTitle();
        repaint();
    }

    protected void setFrameTitle() {
        myFrame.setTitle("Drag mouse to change x. Hold Shift down and drag to change a.");
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
