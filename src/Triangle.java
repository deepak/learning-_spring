import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Triangle implements InitializingBean, DisposableBean {
    private Point pointA;
    private Point pointB;
    private Point pointC;

    public Triangle(Point pointA, Point pointB, Point pointC) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
    }

    public void draw() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "pointA=" + pointA +
                ", pointB=" + pointB +
                ", pointC=" + pointC +
                '}';
    }

    // for InitializingBean
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("triangle created");
    }

    // for DisposableBean
    @Override
    public void destroy() throws Exception {
        System.out.println("triangle destroyed");
    }

    public void initHook() {
        System.out.println("triangle initHook");
    }

    public void destroyHook() {
        System.out.println("triangle destroyHook");
    }
}
