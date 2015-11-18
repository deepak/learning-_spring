import java.util.List;

public class Triangle {
    private List<Point> points;

    public void draw() {
        System.out.println(this);
    }

    public Point getPointA() {
        return points.get(0);
    }

    public Point getPointB() {
        return points.get(1);
    }

    public Point getPointC() {
        return points.get(2);
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "pointA=" + getPointA() +
                ", pointB=" + getPointB() +
                ", pointC=" + getPointC() +
                '}';
    }
}
