package pattrn.U7_Composite;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Шаблоны Java. 7. Компоновщик (Composite)
        //https://www.youtube.com/watch?v=Fj-6Zgn0Nuk&list=PLwcDaxeEINactCC4mly7RQon5juIpH-Q3&index=8
        Shape square1 = new Square();
        Shape square2 = new Square();
        Shape triangle1 = new Triangle();

        Shape square3 = new Square();
        Shape circle1 = new Circle();
        Shape circle2 = new Circle();
        Shape circle3 = new Circle();

        Composite composite = new Composite();
        Composite composite1 = new Composite();
        Composite composite2 = new Composite();

        composite1.addComponent(square1);
        composite1.addComponent(square2);
        composite1.addComponent(triangle1);

        composite2.addComponent(square3);
        composite2.addComponent(circle1);
        composite2.addComponent(circle2);
        composite2.addComponent(circle3);

        composite.addComponent(composite1);
        composite.addComponent(composite2);
        composite.draw();


    }
}

interface Shape{
    void draw();
}

class Square implements Shape{
    @Override
    public void draw() {
        System.out.println("Привет, я квадрат!");

    }
}
class Triangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Привет, я треугольник!");

    }
}
class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("Привет, я круг!");

    }
}

class Composite implements Shape{
    private List<Shape> components = new ArrayList<>();

    void addComponent(Shape component){
        components.add(component);
    }

    void removeComponent(Shape component){
        components.remove(component);
    }

    @Override
    public void draw() {
        for(Shape component: components){
            component.draw();
        }

    }
}