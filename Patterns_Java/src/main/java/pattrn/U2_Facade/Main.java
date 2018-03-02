package pattrn.U2_Facade;

public class Main {
    public static void main(String[] args) {
        //Шаблоны Java. 2. Фасад (U2_Facade)
        //https://www.youtube.com/watch?v=ANlcc2p9kCU&list=PLwcDaxeEINactCC4mly7RQon5juIpH-Q3&index=3

        //это не очень удобно
        Power power = new Power();
        power.on();

        DVDRom dvdRom = new DVDRom();
        dvdRom.load();

        HDD hdd = new HDD();
        hdd.copyFromDVD(dvdRom);
        //это не очень удобно


        //Для удобства сосзаем класс - фасад, и назовем его компьютер
        //а в нем уже и включаем и т.д.
        Computer computer = new Computer();
        computer.copy();




    }
}

class Computer{
    Power power =new Power();
    DVDRom dvdRom = new DVDRom();
    HDD hdd = new HDD();
    void copy(){
        power.on();
        dvdRom.load();
        hdd.copyFromDVD(dvdRom);
    }
}

class Power{
    void on(){
        System.out.println("Включение питания");
    }
    void off(){
        System.out.println("Выключаем питание");
    }
}

class DVDRom{
    private boolean data = false;
    public boolean hasData(){
        return data;
    }
    void load(){
        data = true;
    }
    void unload(){
        data=false;
    }
}

class HDD{
    void copyFromDVD(DVDRom dvd){
        if(dvd.hasData()){
            System.out.println("Происходит копирование с диска");
        }else {
            System.out.println("Вставте диск с данными");
        }
    }
}