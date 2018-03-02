package pattrn.U6_Prototype;

public class Main {
    public static void main(String[] args) {
        //Шаблоны Java. 6 Прототип (Prototype)
        //https://www.youtube.com/watch?v=MGFg2Wb-8CU&list=PLwcDaxeEINactCC4mly7RQon5juIpH-Q3&index=7

        Human original = new Human(18,"Vasya");
        System.out.println(original);


        //когда создавали интерфейс, то указали общий тип Object
        //иначе интерфейс был бы неуниверсальным
        //но когда делаем копию конкретного объекта нудно делать кастинг кокретного класса, который
        //реализовывал интерфейс, т.е. (Human)
        Human copy = (Human)original.copy();
        System.out.println(copy);

        HumanFactory factory = new HumanFactory(copy);
        Human h1 = factory.makeCopy();
        System.out.println(h1);

        factory.setPrototype(new Human(30,"Valeriy"));
        Human h2 = factory.makeCopy();
        System.out.println(h2);


    }
}

interface  Copyable{
    Object copy();
}

class Human implements Copyable{
    int age;
    String name;

    public Human(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Human [age="+ age + ", name=" + name + "}";
    }

    @Override
    public Object copy() {
        Human res = new Human(age,name);
        return res;
    }
}


class HumanFactory{
    Human human;

    public HumanFactory(Human human) {
        setPrototype(human);
    }
    public void setPrototype(Human human){
        this.human = human;
    }
    Human makeCopy(){
        return(Human) human.copy();
    }
}