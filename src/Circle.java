import javax.annotation.Resource;

public class Circle implements Shape {

    private Point center;

    @Resource(name = "pointC")
    public void setCenter(Point center) {
        this.center = center;
    }

    @Override
    public void draw() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "center=" + center +
                '}';
    }
}
