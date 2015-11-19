import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext("spring.xml");
        Triangle shape1 = (Triangle) context.getBean("triangle");

        shape1.draw();
    }
}
