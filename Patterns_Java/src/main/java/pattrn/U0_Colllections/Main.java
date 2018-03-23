package pattrn.U0_Colllections;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        Задача:
        Получить два объекта от разных классов, положить их в один ArrayList
        Затем пробежаться по ArrayList и сделать даункаст, чтобы получить доступ к полям каждого из классов
        */


        List<CollectionType> arr = new ArrayList<>();
        CollectionType car = new Car();
        CollectionType phone = new Phone();

        //Делая апкастинг до интерфейса CollectionType, поэтому у нас нет доступа к полям
//        System.out.println(car.modelCar);
//        System.out.println(phone.typePhone);


        arr.add(car);
        arr.add(phone);

        /*
        Даункас делаем по полю classType который в каждом классе свой
        Даункаст дает нам возможность достучаться через объект до полей каждого класса

        !!!НО, по сути, если мы загоним обработку полей каждого класса (вывод на печать, запись полей в базу данных и т.д.) в метод интерфейса, который мы будем реализовывать в каждом
        классе, то как таковой даункастинг не нужен.

        В этом примере я переопределил метод toString класса Object. Все классы являются наследниками класса Object, поэтому мне не нужно было прописывать этот метод в интерфейсе

        Кроме того, для примера я объявил в интерфейсе свой метод myPrint, который делает тоже самое, что и toString


        */

        for (CollectionType object: arr) {

            switch (object.getTypeCollection()){
                case "car":
                    //работаем без даункаста к классу Car . есть доступ к методам, но нет к полям
                    System.out.println("=============Без даункаста, Car");
                    System.out.println(object);
                    System.out.println(object.myPrint());
                    //работаем с даункастом, есть доступ и к методам и к полям
                    System.out.println("=============С даункастом, Car");
                    Car new1 = (Car) object;
                    System.out.println(new1);
                    System.out.println(new1.myPrint());
                    System.out.println("Доступ к полю modelCar: " + new1.modelCar);
                    break;

                case "phone":
                    //работаем без даункаста к классу Phone . есть доступ к методам, но нет к полям
                    System.out.println("=============Без даункаста, Phone");
                    System.out.println(object);
                    System.out.println(object.myPrint());
                    //работаем с даункастом, есть доступ и к методам и к полям
                    System.out.println("=============С даункастом, Phone");
                    Phone new2 = (Phone) object;
                    System.out.println(new2);
                    System.out.println(new2.myPrint());
                    System.out.println("Доступ к полю typePhone: " + new2.typePhone);
                    break;
            }

        }

        System.out.println("====================Вариант лямбда выражения, без даункаста");
        arr.forEach(e -> System.out.println(e));
        arr.forEach(e -> System.out.println(e.myPrint()));

    }
}

interface CollectionType{
    String getTypeCollection();
    String myPrint();
}


class Car implements CollectionType{
    String modelCar="BMW";
    int speedCar = 220;
    String colorCar="RED";
    String classType="car";


    @Override
    public String getTypeCollection() {
        return classType;
    }

    @Override
    public String toString() {
        return "<toString>>> Car [model=" + modelCar + ", speed=" + speedCar + ", color=" + colorCar+"]";

    }

    @Override
    public String myPrint() {
        return "<myPrint>>> Car [model=" + modelCar + ", speed=" + speedCar + ", color=" + colorCar+"]";

    }
}


class Phone implements CollectionType{
    String typePhone="iPhone";
    String modelPhone="5";
    String colorPhone="BLACK";
    String classType="phone";

    @Override
    public String getTypeCollection() {
        return classType;
    }

    @Override
    public String toString() {
        return "<toString>>> Phone [type=" + typePhone + ", model=" + modelPhone + ", color=" + colorPhone+"]";

    }

    @Override
    public String myPrint() {
        return "<myPrint>>> Phone [type=" + typePhone + ", model=" + modelPhone + ", color=" + colorPhone+"]";

    }
}
