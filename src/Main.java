import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext context = new FileSystemXmlApplicationContext("spring.xml");
        context.registerShutdownHook();

        Triangle shape1 = (Triangle) context.getBean("triangle");

        shape1.draw();
        //sleep(1000l);
    }
}
