import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TriangleRectangle extends JButton implements KeyListener, MouseListener, MouseMotionListener {

    private BufferedImage image;
    private JFrame myFrame = null;
    private int mouseStart = 0;
    private double b = 400;
    private double a = 90000 / b;
    private int aStart = (int) a;
    private boolean drawAnnotation = true;
    private boolean drawAxis = true;
    private int size;
    private double x = 54000 / b;
    private double y = 30000 / a;

    public TriangleRectangle(JFrame frame) {

        myFrame = frame;
        addKeyListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);

        try {
            image = ImageIO.read(new File("clouds.jpg"));
        } catch (IOException e) {
            System.out.println("clouds not found :-/");
        }

        repaint();
    }

    public void paint(Graphics gIn) {

        Graphics2D g2d = (Graphics2D) gIn;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (image != null) {
            setAlpha(g2d, 0.5);
            g2d.drawImage((Image) image, 0, 0, image.getWidth() / 2, (int) (image.getHeight() / 1.8), Color.BLACK, this);
            setAlpha(g2d, 0.7);
        }
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));

        double xPos = (getWidth() - b) / 2.0;
        double yPos = (getHeight() - a) / 2.0;

        Rectangle2D.Double rect = new Rectangle2D.Double(xPos, yPos, b, a);
        g2d.setColor(new Color(190, 215, 240));
        g2d.fill(rect);
        g2d.setColor(new Color(0, 0, 40));
        g2d.draw(rect);

        /// first triangle
        Point2D.Double c1 = new Point2D.Double(xPos, yPos);
        Point2D.Double c2 = new Point2D.Double(xPos, yPos + x);
        Point2D.Double c3 = new Point2D.Double(xPos + b, yPos);
        MyTriangle t1 = new MyTriangle(c1, c2, c3);
        t1.draw(g2d);
        t1.drawCenter(g2d);

        /// second triangle
        c1 = new Point2D.Double(xPos + b, yPos);
        c2 = new Point2D.Double(xPos + b - y, yPos + a);
        c3 = new Point2D.Double(xPos + b, yPos + a);
        MyTriangle t2 = new MyTriangle(c1, c2, c3);
        t2.draw(g2d);
        t2.drawCenter(g2d);

        /// third triangle
        c1 = new Point2D.Double(xPos, yPos + x);
        c2 = new Point2D.Double(xPos, yPos + a);
        c3 = new Point2D.Double(xPos + b - y, yPos + a);
        MyTriangle t3 = new MyTriangle(c1, c2, c3);
        t3.draw(g2d);
        t3.drawCenter(g2d);

        /// middle triangle
        c1 = new Point2D.Double(xPos + b, yPos);
        c2 = new Point2D.Double(xPos + b - y, yPos + a);
        c3 = new Point2D.Double(xPos, yPos + x);
        MyTriangle t4 = new MyTriangle(c1, c2, c3);
        g2d.setColor(new Color(150, 200, 255));
        t4.fill(g2d);
        g2d.setColor(new Color(0, 0, 40));
        t4.draw(g2d);
        t4.drawCenter(g2d);
    }

    private static void setAlpha(Graphics2D g2d, double alpha) {

        AlphaComposite alphaComposite;
        alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) alpha);
        g2d.setComposite(alphaComposite);
    }

    private void drawAnnotations(Graphics2D g2, int originX, int originY) {


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        TriangleRectangle tr = new TriangleRectangle(frame);
        frame.add(tr);
        frame.setLocation(200, 400);
        frame.setSize(1200, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        tr.setFrameTitle();
        tr.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
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
        mouseStart = e.getY();
        aStart = (int) a;
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

        } else {
            a = aStart + delta;
            if (a < 120) {
                a = 120;
            }
            if (a > 600) {
                a = 600;
            }
            b = 90000 / (a);

            x = 54000 / b;
            y = 30000 / a;
        }

        repaint();
    }

    protected void setFrameTitle() {
        myFrame.setTitle("Drag mouse to change aspect ratio.");
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
