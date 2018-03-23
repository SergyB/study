package pattrn.U1_Delegate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {



    public static void main(String[] args) {
        //Шаблоны Java. 1. Делегат (U1_Delegate)
        //https://www.youtube.com/watch?v=qfKX4xQ4Yos&index=2&list=PLwcDaxeEINactCC4mly7RQon5juIpH-Q3

        Painter painter = new Painter();
        painter.setGragics(new Square());
        painter.draw();
        painter.setGragics(new Triangle(10));
        painter.draw();

        Triangle n1 = new Triangle(13);
        n1.draw();
        n1.addDraw();
        System.out.println(n1.i);

        Graphics n2 = new Triangle(18);
        n2.draw();

//        System.out.println(n2.i);
//        n2.addDraw;
        Circle c1 = new Circle();


        List<Graphics> arr = new ArrayList<>();
        arr.add(n1);
        arr.add(n2);
        arr.add(c1);
        System.out.println(arr.size());
        arr.get(0).draw();
//        arr.get(0).addDraw;
        Triangle new1 = (Triangle) arr.get(0);
        new1.addDraw();
        System.out.println(new1.i);


        arr.get(1).draw();
        arr.get(2).draw();

        List<Triangle> arr1 = new ArrayList<>();

        arr1.add(n1);
//        arr1.add(n2);
        arr1.get(0).draw();
        arr1.get(0).addDraw();
        System.out.println(arr1.get(0).i);



        List<Object> arr2 = new ArrayList<>();
        arr2.add(n1);
        arr2.add(n2);
        arr2.add(c1);
        System.out.println(arr2.size());


        System.out.println("===============");
        System.out.println(arr2.get(0).getClass());
        System.out.println("===============");

        if (arr2.get(0)instanceof Triangle){
            System.out.println("===============");
            ((Triangle) arr2.get(0)).addDraw();
        }
//        arr2.get(0).draw();
//        arr.get(0).addDraw;
//        arr2.get(1).draw();
//        arr2.get(2).draw();



    }





}

interface Graphics{
    public void draw();
}

class Triangle implements Graphics{
    public int i = 10;

    Triangle(int val){
        this.i=val;
    }

    @Override
    public void draw() {
        System.out.println("Рисуем треугольник");
    }
    public void addDraw(){
        System.out.println("Дополнительный рисунок");
    }
}
class Square implements Graphics{
    @Override
    public void draw() {
        System.out.println("Рисуем квадрат");
    }
}
class Circle implements Graphics{
    @Override
    public void draw() {
        System.out.println("Рисуем круг");
    }
}

class Painter{
    Graphics gragics;
    void setGragics(Graphics g){
        gragics = g;
    }
    void draw(){
        gragics.draw();
    }
}