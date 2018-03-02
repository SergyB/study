package pattrn.U8_Adapter.Var_1;

public class Main {
    //Шаблоны Java. 8. Адаптер (Adapter)
    //https://www.youtube.com/watch?v=uE0SGhA1QbE&index=9&list=PLwcDaxeEINactCC4mly7RQon5juIpH-Q3
    public static void main(String[] args) {
        //1-ый способ через наследование
        VectorGraphicsInterface gr = new VectorAdapterFromRaster();
        gr.drawLine();
        gr.drawSquare();


    }
}

interface VectorGraphicsInterface{
    void drawLine();
    void drawSquare();
}

class RasterGraphics{
    void drawRasterLine(){
        System.out.println("Рисуем линию.");
    }
    void drawRasterSquare(){
        System.out.println("Рисуем квадрат.");
    }
}

//адаптер
class VectorAdapterFromRaster extends RasterGraphics implements VectorGraphicsInterface{
    @Override
    public void drawLine() {
        drawRasterLine();
    }

    @Override
    public void drawSquare() {
        drawRasterSquare();
    }
}

