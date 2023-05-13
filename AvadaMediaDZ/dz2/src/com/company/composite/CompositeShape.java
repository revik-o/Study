package com.company.composite;

import java.util.ArrayList;

public class CompositeShape implements Shape {

    ArrayList<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        this.shapes.remove(shape);
    }

    @Override
    public void create() {
        this.shapes.forEach(Shape::create);
    }
}
