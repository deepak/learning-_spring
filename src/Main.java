import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("spring.xml");

        Triangle shape1 = (Triangle) context.getBean("triangle");
        Triangle shape2 = (Triangle) context.getBean("triangle");

        Point point1 = (Point) context.getBean("point-0");
        Point point2 = (Point) context.getBean("point-0");

        shape1.draw();

        System.out.println("shapes are equal: " + shape1.equals(shape2));
        System.out.println("point in shapes are equal: " + shape1.getPointA().equals(shape2.getPointA()));
        System.out.println("points are equal: " + point1.equals(point2));
    }
}
