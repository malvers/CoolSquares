import java.awt.*;
import java.awt.geom.CubicCurve2D;

public class CurlyBrace {

    private Point ti;
    private Point p3;
    private Point c1;
    private Point c2;
    private Point c4;
    private Point c3;
    private Point p1;
    CubicCurve2D shape1 = new CubicCurve2D.Float();
    CubicCurve2D shape2 = new CubicCurve2D.Float();

    public CurlyBrace(Point pIn, int length, int width) {

        if (pIn == null || length <= 0) {
            return;
        }

        p1 = pIn;
//        int width = length / 12;

        init(length, width);
    }

    public void init(int length, int width) {

        ti = new Point(p1.x + width, p1.y + length / 2);
        p3 = new Point(p1.x, p1.y + length);

        c1 = new Point((int) (p1.x + width), p1.y);
        int eccentricity = length / 200;
        c2 = new Point((int) (p1.x - width / 3), ti.y - eccentricity);

        c3 = new Point((int) (p1.x - width / 3), ti.y + eccentricity);
        c4 = new Point((int) (p1.x + width), p3.y);

        shape1.setCurve(p1.x, p1.y, c1.x, c1.y, c2.x, c2.y, ti.x, ti.y);
        shape2.setCurve(ti.x, ti.y, c3.x, c3.y, c4.x, c4.y, p3.x, p3.y);
    }

    public void draw(Graphics g, boolean drawControlPoints) {

        Graphics2D g2 = (Graphics2D) g;
        g2.draw(shape1);
        g2.draw(shape2);
        if (drawControlPoints) {
            drawControlPoints(g2, p1, ti, p3, c1, c2, c3, c4);
        }
    }

    private void drawControlPoints(Graphics2D g, Point p1, Point tip, Point p3, Point c1, Point c2, Point c3, Point c4) {

        Graphics2D g2 = g;
        g2.setColor(new Color(180, 0, 0));
        g2.drawRect(p1.x - 1, p1.y - 1, 2, 2);
        g2.drawRect(tip.x - 1, tip.y - 1, 2, 2);
        g2.drawRect(p3.x - 1, p3.y - 1, 2, 2);

        g2.setColor(new Color(180, 0, 200));
        g2.drawRect(c1.x - 1, c1.y - 1, 2, 2);
        g2.drawRect(c2.x - 1, c2.y - 1, 2, 2);

        g2.setColor(new Color(0, 180, 80));
        g2.drawRect(c3.x - 1, c3.y - 1, 2, 2);
        g2.drawRect(c4.x - 1, c4.y - 1, 2, 2);
    }
}
