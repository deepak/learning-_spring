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

        Triangle shape1 = (Triangle) context.getBean("triangle");
        Triangle shape2 = (Triangle) context.getBean("shape");
        Triangle shape3 = (Triangle) context.getBean("t1");
        Triangle shape4 = (Triangle) context.getBean("s1");
        Triangle shape5 = (Triangle) context.getBean("t2");

        System.out.println("shape1: ");
        shape1.draw();

        System.out.println("shape2: ");
        shape2.draw();

        System.out.println("shape3: ");
        shape3.draw();

        System.out.println("shape4: ");
        shape4.draw();

        System.out.println("shape5: ");
        System.out.println(shape5);

        //System.out.println("both beans are equal: " + shape1.equals(shape2));
    }
}
