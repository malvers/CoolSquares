import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class CurlyBraceOnline extends Frame {

    int preX, preY;


    public CurlyBraceOnline() {
        super("Java AWT Examples");
        prepareGUI();
    }

    public static void main(String[] args) {
        CurlyBraceOnline awtGraphicsDemo = new CurlyBraceOnline();
        awtGraphicsDemo.setVisible(true);
    }

    private void prepareGUI() {
        setSize(400, 400);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {

        drawCurlyBrace((Graphics2D) g);

    }

    private void drawCurlyBrace(Graphics2D g) {
        CubicCurve2D shape1 = new CubicCurve2D.Float();
        shape1.setCurve(150F, 100F, 200F, 90F, 130F, 200F, 190F, 220F);
        CubicCurve2D shape2 = new CubicCurve2D.Float();
        shape2.setCurve(190F, 220F, 140F, 230F, 190F, 350F, 150F, 340F);

        Graphics2D g2 = g;
        g2.draw(shape1);
        g2.draw(shape2);
    }
}