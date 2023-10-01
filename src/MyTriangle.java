import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class MyTriangle {

    private final Point2D.Double center;
    private double myArea = -1;
    private Point2D.Double c1;
    private Point2D.Double c2;
    private Point2D.Double c3;
    private Path2D.Double myShape;

    public MyTriangle(Point2D.Double c1In, Point2D.Double c2In, Point2D.Double c3In) {

        c1 = c1In;
        c2 = c2In;
        c3 = c3In;

        myShape = new Path2D.Double();
        myShape.moveTo(c1.x, c1.y);
        myShape.lineTo(c2.x, c2.y);
        myShape.lineTo(c3.x, c3.y);
        myShape.closePath();

        myArea = calculateArea();

        center = calculateCenter(c1, c2, c3);
    }

    public void drawCenter(Graphics2D g2d) {

        g2d.setFont(new Font("Arial", Font.PLAIN, 12));

//        g2d.fillRect((int) center.x - 2, (int) (center.y - 2), 4, 4);

        int area = (int) Math.ceil(myArea);
        String sArea = "A=" + (int) (area / 1000);

        FontMetrics fontMetrics = g2d.getFontMetrics();
        int width = fontMetrics.stringWidth(sArea);
        int height = fontMetrics.getHeight();

        g2d.drawString(sArea, (int) (center.x - (width / 2)), (int) center.y + (height / 2));
    }

    public void draw(Graphics2D g2d) {
        g2d.draw(myShape);
    }

    public void fill(Graphics2D g2d) {
        g2d.fill(myShape);
    }

    private Point2D.Double calculateCenter(Point2D.Double A, Point2D.Double B, Point2D.Double C) {

        double centerX = (A.getX() + B.getX() + C.getX()) / 3.0;
        double centerY = (A.getY() + B.getY() + C.getY()) / 3.0;
        return new Point2D.Double(centerX, centerY);
    }

    private double calculateArea() {

        Point2D.Double c2c1 = new Point2D.Double(c2.getX() - c1.getX(), c2.getY() - c1.getY());
        Point2D.Double c3c1 = new Point2D.Double(c3.getX() - c1.getX(), c3.getY() - c1.getY());

        double crossProduct = crossProduct(c2c1, c3c1);

        return 0.5 * Math.abs(crossProduct);
    }

    private static double crossProduct(Point2D.Double vector1, Point2D.Double vector2) {
        return vector1.getX() * vector2.getY() - vector1.getY() * vector2.getX();
    }

    public double getArea() {
        if (myArea < 0) {
            calculateArea();
        }
        return myArea;
    }
}









