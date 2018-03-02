package pattrn.U9_Decorator;

import java.lang.ref.SoftReference;

public class Main {
    public static void main(String[] args) {
        //Шаблоны Java. 9. Декоратор (Decorator / Wrapper)
        //https://www.youtube.com/watch?v=X7-3wQElWd4&list=PLwcDaxeEINactCC4mly7RQon5juIpH-Q3&index=10
        PrinterInterface printer = new Printer("Hello!");

        //пустой декоратор
        PrinterInterface printer1 = new QuotesDecorator_empty(new Printer("Hello!"));

        //декоратор - кавычки
        PrinterInterface printer2 = new QuotesDecorator(new Printer("Hello!"));

        //вложенный декоратор
        PrinterInterface printer3 = new RightBracketDecorator(new LeftBracketDecorator(new Printer("Hello!")));

        //вложенный декоратор, если поменяем местами лефт и райт, то ничего не поменяется в результате
        PrinterInterface printer4 = new LeftBracketDecorator(new RightBracketDecorator(new Printer("Hello!")));

        //с классом Decorator
        //вложенный декоратор, если поменяем местами лефт и райт, то ничего не поменяется в результате
        PrinterInterface printer5 = new DecoratorQuotesDecorator(new DecoratorLeftBracketDecorator(new DecoratorRightBracketDecorator(new Printer("Hello!"))));


        printer.print();
        System.out.println("");

        printer1.print();
        System.out.println("");

        printer2.print();
        System.out.println("");

        printer3.print();
        System.out.println("");

        printer4.print();
        System.out.println("");

        printer5.print();
        System.out.println("");

    }
}

interface PrinterInterface{
    void print();
}

class Printer implements PrinterInterface{
    String value;

    public Printer(String value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.print(value);
    }

}

//класс-деоратор, пустой
class QuotesDecorator_empty implements PrinterInterface{
    PrinterInterface component;

    public QuotesDecorator_empty(PrinterInterface component) {
        this.component = component;
    }

    @Override
    public void print() {
        component.print();
    }
}

//класс-деоратор, который добавляет ковычки
class QuotesDecorator implements PrinterInterface{
    PrinterInterface component;

    public QuotesDecorator(PrinterInterface component) {
        this.component = component;
    }

    @Override
    public void print() {
        System.out.print("\"");
        component.print();
        System.out.print("\"");
    }
}

//класс-деоратор, левая скобка
class LeftBracketDecorator implements PrinterInterface{
    PrinterInterface component;

    public LeftBracketDecorator(PrinterInterface component) {
        this.component = component;
    }

    @Override
    public void print() {
        System.out.print("[");
        component.print();
    }
}

//класс-деоратор, левая правая
class RightBracketDecorator implements PrinterInterface{
    PrinterInterface component;

    public RightBracketDecorator(PrinterInterface component) {
        this.component = component;
    }

    @Override
    public void print() {
        component.print();
        System.out.print("]");
    }
}

//Чтобы объединить три декоратора, создаем абстрактный класс Decorator
//с общим кодом 3х декораторов
//    PrinterInterface component; + конструктор + метод принт
abstract class Decorator implements PrinterInterface{
    PrinterInterface component;

    public Decorator(PrinterInterface component) {
        this.component = component;
    }

    public void print(){
        component.print();
    }
}
//класс-деоратор, который добавляет ковычки
class DecoratorQuotesDecorator extends Decorator{
    public DecoratorQuotesDecorator(PrinterInterface component) {
        super(component);
    }

    @Override
    public void print() {
        System.out.print("\"");
        super.print();
        System.out.print("\"");
    }
}

//класс-деоратор, левая скобка
class DecoratorLeftBracketDecorator extends Decorator{
    public DecoratorLeftBracketDecorator(PrinterInterface component) {
        super(component);
    }

    @Override
    public void print() {
        System.out.print("[");
        super.print();
    }
}

//класс-деоратор, левая правая
class DecoratorRightBracketDecorator extends Decorator{
    public DecoratorRightBracketDecorator(PrinterInterface component) {
        super(component);
    }

    @Override
    public void print() {
        super.print();
        System.out.print("]");
    }
}
