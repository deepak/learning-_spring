package org.deepak.springdemo.di;

import org.springframework.beans.factory.BeanInitializationException;

public class Triangle implements Shape {
    private Point pointA;
    private Point pointB;
    private Point pointC;

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

    public void setPointC(Point pointC) {
        this.pointC = pointC;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "pointA=" + pointA +
                ", pointB=" + pointB +
                ", pointC=" + pointC +
                '}' + " next x: " + (pointC.getX() + 1);
    }
}
