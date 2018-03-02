package pattrn.U5_Builder.NormalBuilder;



public class Main_normal {
    public static void main(String[] args) {
        //Шаблоны Java. 5. Строитель (Builder)
        //https://www.youtube.com/watch?v=kn9H6e5hwMY&index=6&list=PLwcDaxeEINactCC4mly7RQon5juIpH-Q3

        Director director = new Director();
        director.setBuilder(new SubaruBuilder());
        Car car = director.buildCar();
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

abstract class CarBuilder {
    Car car;
    void createCar(){car = new Car();}

    abstract void buildMake();
    abstract void buildTransmission();
    abstract void buildMaxSpeed();

    Car getCar(){return car;}
}

class FordMondeoBuilder extends CarBuilder{
    @Override
    void buildMake() {
    car.setMake("Ford Modeo");
    }

    @Override
    void buildTransmission() {
    car.setTransmission(Transmission.AUTO);
    }

    @Override
    void buildMaxSpeed() {
        car.setMaxSpeed(260);

    }
}

class SubaruBuilder extends CarBuilder{
    @Override
    void buildMake() {
        car.setMake("Subaru");
    }

    @Override
    void buildTransmission() {
        car.setTransmission(Transmission.MANUAL);
    }

    @Override
    void buildMaxSpeed() {
        car.setMaxSpeed(320);

    }
}

class Director{
    CarBuilder builder;

    void setBuilder(CarBuilder b){
        builder = b;
    }

    Car buildCar(){
        builder.createCar();
        builder.buildMake();
        builder.buildTransmission();
        builder.buildMaxSpeed();
        Car car = builder.getCar();
        return car;
    }
}