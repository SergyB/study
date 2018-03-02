package pattrn.U5_Builder.SimpleBuilder;

public class Main_simple {
    public static void main(String[] args) {
        //Шаблоны Java. 5. Строитель (Builder)
        //https://www.youtube.com/watch?v=kn9H6e5hwMY&index=6&list=PLwcDaxeEINactCC4mly7RQon5juIpH-Q3

        Car car = new CarBuilder()
                .buildMake("Mersedes")
                .buildTransmission(Transmission.AUTO)
                .buildMaxSpeed(280)
                .build();
        System.out.println(car);


    }
}

enum Transmission{
    MANUAL,AUTO
}

class Car{
    String make;
    Transmission transmission;
    int maxSpeed;

    public void setMake(String make) {
        this.make = make;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car [make=" + make + ", transmission=" + transmission + ", maxSpeed=" + maxSpeed+"]";
    }
}

class CarBuilder{
    //переменные со значениями по умолчанию
    String m = "Default";
    Transmission t = Transmission.MANUAL;
    int s = 120;

    CarBuilder buildMake(String m){
        this.m = m;
        return this; // возвращаем CarBuilder - это дает возможность писать методы через точку один за другим
    }

    CarBuilder buildTransmission(Transmission t){
        this.t = t;
        return this; // возвращаем CarBuilder - это дает возможность писать методы через точку один за другим
    }

    CarBuilder buildMaxSpeed(int s){
        this.s = s;
        return this; // возвращаем CarBuilder - это дает возможность писать методы через точку один за другим
    }

    Car build(){
        Car car = new Car();
        car.setMake(m);
        car.setTransmission(t);
        car.setMaxSpeed(s);
        return car; //возвращаем объект машины, точек в билдере больше не нужно
    }
}