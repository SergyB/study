package pattrn.U_100_Test_1;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static List<Object> arr;

    public static void main(String[] args) {

        arr = new ArrayList<>();

        A i = new A();
        System.out.println(i.i);
        B s = new B();
        System.out.println(s.s);



        arr.add(s);
        arr.add(i);

        arr.get(0);


    }


}

class A{
    Integer i = 10;
}

class B{
    String s = "String";
}

