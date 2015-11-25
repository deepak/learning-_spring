package org.deepak.springdemo.di;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

public class Triangle implements Shape {
    private Point pointA;
    private Point pointB;
    private Point pointC;
    private String shapeName;

    public Triangle(Point pointA, Point pointB, Point pointC) {
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
    }

    public Triangle(Point pointA, Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }


    public void draw() {
        System.out.println(this);
    }

    public void init() throws BeanInitializationException {
        if (pointA == null || pointB == null || pointC == null) {
            throw new BeanInitializationException("triangle does not have required fields");
        }
    }

    @Resource
    public void setShapeName(@Value("${shape.triangle.name}") String shapeName) {
        this.shapeName = shapeName;
    }

    public void setPointC(Point pointC) {
        this.pointC = pointC;
    }

    @Override
    public String toString() {
        return shapeName + '{' +
                "pointA=" + pointA +
                ", pointB=" + pointB +
                ", pointC=" + pointC +
                '}' + " next x: " + (pointC.getX() + 1);
    }
}
