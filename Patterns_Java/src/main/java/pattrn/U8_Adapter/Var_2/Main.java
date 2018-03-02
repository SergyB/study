package pattrn.U8_Adapter.Var_2;

public class Main {
    //Шаблоны Java. 8. Адаптер (Adapter)
    //https://www.youtube.com/watch?v=uE0SGhA1QbE&index=9&list=PLwcDaxeEINactCC4mly7RQon5juIpH-Q3
    public static void main(String[] args) {
        //2-й способ через копозицию
        //можно задавать экземпляр класса явно
        //VectorGraphicsInterface gr = new VectorAdapterFromRaster();

        //а можно передавать его, при создании объекта
        VectorGraphicsInterface gr = new VectorAdapterFromRaster(new RasterGraphics());
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
//нет  extends RasterGraphics но создаем экземпляр класса
class VectorAdapterFromRaster implements VectorGraphicsInterface {
    //можно задавать экземпляр класса явно
    //RasterGraphics rasterGraphics = new RasterGraphics();

    //а можно передавать его, при создании объекта
    RasterGraphics rasterGraphics;
    public VectorAdapterFromRaster(RasterGraphics raster) {
        this.rasterGraphics = raster;
    }

    @Override
    public void drawLine() {
        rasterGraphics.drawRasterLine();
    }

    @Override
    public void drawSquare() {
        rasterGraphics.drawRasterSquare();
    }
}
