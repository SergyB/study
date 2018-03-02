package pattrn.U3_FactoryMethod;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        //Шаблоны Java. 3. Фабричный метод (Factory Method)
        //https://www.youtube.com/watch?v=HZ4ciLNWX4E&index=4&list=PLwcDaxeEINactCC4mly7RQon5juIpH-Q3

        //это без ментода фабрик
        Watch watch = new DigitalWatch();
        watch.showTime();
        //или так
        Watch watch1 = new RomeWatch();
        watch1.showTime();
        //это без ментода фабрик

        //метод фабрик нужен, чтоб не менялся клиентский код.
        //ему просто подсовывают разные часы

        //это пространство подсовывания
        //Вариант один посовывание
        //WatchMaker maker = new DigitalWatchMaker();

        //Вариант два посовывание
        WatchMaker maker = getMakerByName("Digital");


        //это неизменный клиентсткий код
        Watch watch2 = maker.createWatch();
        watch.showTime();
        //это неизменный клиентсткий код
    }

    public static WatchMaker getMakerByName(String maker){
        if(maker.equals("Digital")){
           return new DigitalWatchMaker();
        }else if(maker.equals("Rome"))
            return new RomelWatchMaker();

        throw new RuntimeException("Не поддерживаемая производственная линия часов: " + maker);
    }
}
//это без ментода фабрик
interface Watch{
    void showTime();
}
class DigitalWatch implements Watch{
    @Override
    public void showTime() {
        System.out.println(new Date());
    }
}
class RomeWatch implements Watch{
    @Override
    public void showTime() {
        System.out.println("VII-XX");
    }
}
//это без ментода фабрик

//теперь создаем фабричный метод, в этом примере он один WatchMaker
interface WatchMaker{
    //в нем один фабричный метод, который возвращает ссылку на Watch и метод createWatch
    Watch createWatch();
}
//теперь реализуем производителей
class DigitalWatchMaker implements WatchMaker{
    @Override
    public Watch createWatch() {
        return new DigitalWatch();
    }
}
class RomelWatchMaker implements WatchMaker{
    @Override
    public Watch createWatch() {
        return new RomeWatch();
    }
}
