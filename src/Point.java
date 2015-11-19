import org.springframework.beans.factory.BeanNameAware;

public class Point implements BeanNameAware {
    private Integer x,y;
    private String beanName = null;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                "} (beanName: " + beanName + ')';
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
