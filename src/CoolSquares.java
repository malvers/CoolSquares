import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CoolSquares extends JButton implements KeyListener, MouseListener, MouseMotionListener {

    private BufferedImage image;
    private JFrame myFrame = null;
    int x = 120;
    int a = 80;

    private int xStart = x;
    private int aStart = a;
    private int mouseStart = 0;
    private boolean drawAnnotation = true;
    private boolean drawAxis = true;
    private int size;

    private CurlyBrace cb = null;

    public CoolSquares(JFrame frame) {
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
            float alpha = 0.5f; // Adjust this value between 0.0f (fully transparent) and 1.0f (fully opaque)
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(alphaComposite);
            g2d.drawImage((Image) image, 0, 0, image.getWidth() / 2, (int) (image.getHeight() / 1.8), Color.BLACK, this);
            alpha = 0.85f; // Adjust this value between 0.0f (fully transparent) and 1.0f (fully opaque)
            alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(alphaComposite);
        }
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));

        int grey = 160;

        int originX = getWidth() / 2;
        int originY = getHeight() / 2;

        int posX = originX;
        int posY = originY;

        /// draw center ///////////////////////////////
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(posX, posY, a, a);

        /// draw first to the right of center ////////
        posX = originX + a;
        posY = (int) (originY - x);
        size = (int) (a + x);
        g2d.setColor(new Color(190, 215, 240));
        g2d.fillRect(posX, posY, size, size);
        g2d.setColor(new Color(grey, grey, grey));
        g2d.drawRect(posX, posY, size, size);

        /// draw first below center //////////////////
        posX = originX;
        posY = originY + a;
        size = (int) (2 * a + x);
        g2d.setColor(new Color(190, 215, 240));
        g2d.fillRect(posX, posY, size, size);
        g2d.setColor(new Color(grey, grey, grey));
        g2d.drawRect(posX, posY, size, size);

        /// draw lower left below center /////////////
        size = (int) (3 * a + x);
        posX = originX - size;
        posY = originY;
        g2d.setColor(new Color(190, 215, 240));
        g2d.fillRect(posX, posY, size, size);
        g2d.setColor(new Color(grey, grey, grey));
        g2d.drawRect(posX, posY, size, size);

        /// draw upper left below center /////////////
        posX = originX - size;
        posY = originY - size - a;
        size = (int) (4 * a + x);
        g2d.setColor(new Color(190, 215, 240));
        g2d.fillRect(posX, posY, size, size);
        g2d.setColor(new Color(grey, grey, grey));
        g2d.drawRect(posX, posY, size, size);

        /// draw green ///////////////////////////////
        posX = originX + a;
        posY = originY - size;
        size = 4 * a;
        g2d.setColor(new Color(146, 208, 80));
        g2d.fillRect(posX, posY, size, size);
        g2d.setColor(new Color(grey, grey, grey));
        g2d.drawRect(posX, posY, size, size);

        if (drawAnnotation) {
            drawAnnotations(g2d, originX, originY);
        }
    }

    private void drawAnnotations(Graphics2D g2d, int originX, int originY) {

        int aa = a / 10;
        int val = aa * aa;
        String str = "";
        Rectangle2D b;

        /// draw x ///////////////////////////////////
        if (drawAxis) {

            g2d.setColor(new Color(160, 0, 0));
//            g2d.setStroke(new BasicStroke(2));
//            g2d.setColor(new Color(0, 0, 40));
            g2d.drawLine(originX + a, originY, originX + a, (int) (originY - x));
            g2d.drawString("x = " + x / 10, originX + a + 14, (int) (originY - x / 2 + 5));

            Point startBrace = new Point(originX + a, originY - x);
            cb = new CurlyBrace(startBrace, x, 12);

            g2d.setStroke(new BasicStroke(1));
            cb.draw(g2d, false);
        }

        /// draw y ///////////////////////////////////
        if (drawAxis) {

            Point from = new Point(originX + a, (int) (originY - x));
            Point to = new Point(originX + a, (int) (originY - x - 4 * a));
            int length = from.y - to.y;

            Point startBrace = new Point(originX + a, (int) (originY - x - 4 * a));
            cb = new CurlyBrace(startBrace, length, -12);
            g2d.setColor(new Color(0, 0, 40));
            g2d.setStroke(new BasicStroke(1));
            cb.draw(g2d, false);

//            g2.setColor(new Color(0, 0, 80));

            str = "y = " + aa * 4;
            b = g2d.getFontMetrics().getStringBounds(str, g2d).getBounds();
//            g2.drawLine(from.x, from.y, to.x, to.y);
            g2d.drawString(str, (int) (originX - b.getWidth() + a - 16), originY - x - (2 * a) + 4);
        }

        /// draw a area
        g2d.setFont(new Font("Arial", Font.PLAIN, 24));
        str = "" + (aa * 4 * aa * 4);
        b = g2d.getFontMetrics().getStringBounds(str, g2d).getBounds();
        g2d.setColor(new Color(0, 0, 40));
        g2d.drawString(str, (int) (originX + a + a * 2 - b.getWidth() / 2), originY - x - (2 * a) + 4);

        /// draw a ///////////////////////////////////
        str = "a = " + a / 10;
        if (drawAxis) {

            Point startBrace = new Point(originX + a, originY);
            cb = new CurlyBrace(startBrace, a, 12);
//            g2.setColor(new Color(0, 0, 40));
//            g2.setStroke(new BasicStroke(1));
            cb.draw(g2d, false);

            g2d.setFont(new Font("Arial", Font.PLAIN, 12));
//            g2.setColor(new Color(0, 0, 80));
//            g2.setStroke(new BasicStroke(2));

//            g2.drawLine(originX + a, originY, originX + a, originY + a);
            g2d.drawString(str, originX + a + 14, originY + a / 2 + 4);
        }

        /// draw a area
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        str = "" + val;
        b = g2d.getFontMetrics().getStringBounds(str, g2d).getBounds();
        g2d.setColor(new Color(0, 0, 40));
        g2d.drawString("" + val, (int) (originX + (a / 2) - (b.getWidth() / 2.0)), originY + a / 2 + 6);

        g2d.setStroke(new BasicStroke(1));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        CoolSquares coolSquares = new CoolSquares(frame);
        frame.add(coolSquares);
        frame.setLocation(200, 400);
        frame.setSize(1200, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        coolSquares.setFrameTitle();
        coolSquares.repaint();
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
