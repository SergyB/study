package pattrn.U4_AbstractFactory;

public class Main {
    public static void main(String[] args) {
        //Шаблоны Java. 4. Абстрактная фабрика (Abstract Factory)
        //https://www.youtube.com/watch?v=FYX9l5OQtJE&index=5&list=PLwcDaxeEINactCC4mly7RQon5juIpH-Q3

        //Абстрактная фабрика использует внутри себя несколько фабричных методов
        //Абстрактная фабрика будет производить разныке объекты, для продукта А и В
        //В U3_FactoryMethod у нас был только один продукт  -  продукта А
        //В этом примерер мы делаем мышку, клавиатуру и тачпад и две имплементации  - русскую и английскую
        //А можем легко добавить еще и французкую реализацию


        DeviceFactory factory = getFactoryByContryCode("RU");

        //удобство тоже самое - неизменность клиентского кода
        //клиентский код
        Mouse m = factory.getMouse();
        Keyboard k = factory.getKeyboard();
        Touchpad t = factory.getTouchpad();

        m.click();
        m.dblclick();
        k.print();
        k.println();
        t.track(10, 35);
        //клиентский код
    }


    //метод, который возвращает фабрику по стране
    public static DeviceFactory getFactoryByContryCode(String lang){
        switch (lang){
            case "RU":
                return new RuDeviseFactory();
            case "EN":
                return new EnDeviseFactory();
            default:
                throw new RuntimeException("Usupported Coutry code: " + lang);
        }

    }
}

    interface Mouse{
        void click();
        void dblclick();
        void scroll(int derection);
    }
    interface Keyboard{
        void print();
        void println();
    }
    interface Touchpad{
        void track(int deltaX,int deltaY);
    }

    //этот интерфейс наша фабрика
    //фабрика будет возвращать три устройства
    interface DeviceFactory{
        Mouse getMouse();
        Keyboard getKeyboard();
        Touchpad getTouchpad();
    }

    //английская фабрика
    class EnDeviseFactory implements DeviceFactory{
        @Override
        public Mouse getMouse() {
            return new EnMouse();
        }
        @Override
        public Keyboard getKeyboard() {
            return new EnKeyboard();
        }

        @Override
        public Touchpad getTouchpad() {
            return new EnTouchpad();
        }
    }

    //русская фабрика
    class RuDeviseFactory implements DeviceFactory{
        @Override
        public Mouse getMouse() {
            return new RuMouse();
        }
        @Override
        public Keyboard getKeyboard() {
            return new RuKeyboard();
        }

        @Override
        public Touchpad getTouchpad() {
            return new RuTouchpad();
        }
    }

    // русская имплементация
    class RuMouse implements Mouse{
        @Override
        public void click(){
            System.out.println("Щелчок мышью");
        }
        @Override
        public void dblclick(){
            System.out.println("Двойной щелчок мышью");
        }
        @Override
        public void scroll(int derection) {
            if(derection>0)
                System.out.println("Скроллим вверх");
            else if (derection<0)
                System.out.println("Скроллим вниз");
            else
                System.out.println("Не скроллим");
        }
    }

    class RuKeyboard implements Keyboard{
        @Override
        public void print() {
            System.out.println("Печатаем строку");
        }

        @Override
        public void println() {
            System.out.println("Печатаем строку с переводом строки");
        }
    }

    class RuTouchpad implements Touchpad{
        @Override
        public void track(int deltaX, int deltaY) {
            int s = (int) Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaX,2));
            System.out.println("Передвинулись на " + s + "пикселей");
        }
    }

    //английская имплементация
    class EnMouse implements Mouse{
        @Override
        public void click(){
            System.out.println("Mouse click");
        }
        @Override
        public void dblclick(){
            System.out.println("Mouse double click");
        }
        @Override
        public void scroll(int derection) {
            if(derection>0)
                System.out.println("Scroll up");
            else if (derection<0)
                System.out.println("Scroll down");
            else
                System.out.println("No scrolling");
        }
    }

    class EnKeyboard implements Keyboard{
        @Override
        public void print() {
            System.out.println("Print");
        }

        @Override
        public void println() {
            System.out.println("Print line");
        }
    }

    class EnTouchpad implements Touchpad{
        @Override
        public void track(int deltaX, int deltaY) {
            int s = (int) Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaX,2));
            System.out.println("Moved " + s + "pixels");
        }
    }



