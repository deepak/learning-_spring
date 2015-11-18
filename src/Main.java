import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("spring.xml");

        Point point = null;
        try {
            point = (Point) context.getBean("inner-bean");
        } catch (Exception e) {
            System.out.println("cannot refer to inner bean");
        }
        System.out.println("inner bean: " + point);

        Triangle shape = (Triangle) context.getBean("triangle");

        shape.draw();
    }
}
