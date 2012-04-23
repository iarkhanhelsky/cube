 
package Cube;

/**
 * Класс RGB
 * Класс представляющий RGB цвет как вектор и реализующий элементарные операции над ним
 * как над вектором (вычитание, сложение, умножение на скаляр).
 * Ни один из методов не меняет оригинал, все возвращают новые объекты.
 * @author Архангельский Илья
 */
public class RGB
{

    private double r;
    private double g;
    private double b;

    /**
     * Создает цвет в пространстве RGB. Каждая компонента задается диапазоном значений 0.0 - 1.0,
     * где 0.0 - значит что компонента абсолютно непрозрачна, 1.0 - абсолютно прозрачна
     * @param r
     * @param g
     * @param b
     */
    public RGB(double r, double g, double b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

   
    // Геттеры и сеттеры
    public double getB()
    {
        return b;
    }

    public void setB(double b)
    {
        this.b = b;
    }

    public double getG()
    {
        return g;
    }

    public void setG(double g)
    {
        this.g = g;
    }

    public double getR()
    {
        return r;
    }

    public void setR(double r)
    {
        this.r = r;
    }

    /**
     * Вычитание RGB вектора
     * @param x RGB вектор
     * @return this - x
     */
    public RGB diff (RGB x)
    {
        return new RGB (this.r-x.r,this.g-x.g,this.b-x.b);
    }

    /**
     * Умножение RGB вектора на скаляр
     * @param x
     * @return x*this
     */
    public RGB mulScalar (double x)
    {
        return new RGB (this.r*x,this.g*x,this.b*x);
    }

    /**
     * Cложение RGB векторов
     * @param x
     * @return this+x
     */
    public RGB sum (RGB x)
    {
        return new RGB (this.r+x.r,this.g+x.g,this.b+x.b);
    }
}


