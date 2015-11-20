import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        AbstractApplicationContext context = new FileSystemXmlApplicationContext("spring.xml");
        context.registerShutdownHook();

        Triangle shape1 = (Triangle) context.getBean("triangle");
        Person person = (Person) context.getBean("person");

        shape1.draw();
        System.out.println(person);
    }
}
