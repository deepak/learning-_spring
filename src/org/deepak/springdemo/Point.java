package org.deepak.springdemo;

public class Point {
    private Integer x,y;

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getX() {
        return x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "org.deepak.springdemo.Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
