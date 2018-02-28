package pattrn.Delegate1;

public class Main {
    public static void main(String[] args) {
    //Шаблоны Java. 1. Делегат (Delegate1)
    //https://www.youtube.com/watch?v=qfKX4xQ4Yos&index=2&list=PLwcDaxeEINactCC4mly7RQon5juIpH-Q3

    Painter painter = new Painter();
    painter.setGragics(new Square());
    painter.drow();
    painter.setGragics(new Triangle());
    painter.drow();
    }


}

interface Gragics{
    public void draw();
}

class Triangle implements Gragics{
    @Override
    public void draw() {
        System.out.println("Рисуем треугольник");
    }
}
class Square implements Gragics{
    @Override
    public void draw() {
        System.out.println("Рисуем квадрат");
    }
}
class Circle implements Gragics{
    @Override
    public void draw() {
        System.out.println("Рисуем круг");
    }
}

class Painter{
    Gragics gragics;
    void setGragics(Gragics g){
        gragics = g;
    }
    void drow(){
        gragics.draw();
    }
}