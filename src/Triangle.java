import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

//https://spring.io/understanding/application-context
public class Triangle implements ApplicationContextAware {
    private Point pointA;
    private Point pointB;
    private Point pointC;

    private ApplicationContext context = null;

    public Triangle(Point pointA, Point pointB, Point pointC) {
        System.out.println("calling constructor with point A, B and C");
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
    }

    public Triangle(Point pointA, Point pointB) {
        System.out.println("calling constructor with point A and B");
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    public Point getPointC() {
        return pointC;
    }

    public void setPointC(Point pointC) {
        this.pointC = pointC;
    }

    public void draw() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "pointA=" + getPointA() +
                ", pointB=" + getPointB() +
                ", pointC=" + getPointC() +
                '}';
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setting application context");
        this.context = applicationContext;
        this.pointC = (Point) this.context.getBean("pointX");
    }
}
