
package org.dmlaps.gfx;

/**
 * Класс ColorRGB
 * Класс представляющий ColorRGB цвет как вектор и реализующий элементарные операции над ним
 * как над вектором (вычитание, сложение, умножение на скаляр).
 * Ни один из методов не меняет оригинал, все возвращают новые объекты.
 *
 * @author Ilya Arkhanhelsky
 */
public class ColorRGB
{

    private double r;
    private double g;
    private double b;

    /**
     * Создает цвет в пространстве ColorRGB. Каждая компонента задается диапазоном значений 0.0 - 1.0,
     * где 0.0 - значит что компонента абсолютно непрозрачна, 1.0 - абсолютно прозрачна
     *
     * @param r
     * @param g
     * @param b
     */
    public ColorRGB(double r, double g, double b)
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


    public double getG()
    {
        return g;
    }


    public double getR()
    {
        return r;
    }


    /**
     * Вычитание ColorRGB вектора
     *
     * @param x ColorRGB вектор
     * @return this - x
     */
    public ColorRGB diff(ColorRGB x)
    {
        return new ColorRGB(this.r - x.r, this.g - x.g, this.b - x.b);
    }

    /**
     * Умножение ColorRGB вектора на скаляр
     *
     * @param x
     * @return x*this
     */
    public ColorRGB mulScalar(double x)
    {
        return new ColorRGB(this.r * x, this.g * x, this.b * x);
    }

    /**
     * Cложение ColorRGB векторов
     *
     * @param x
     * @return this+x
     */
    public ColorRGB sum(ColorRGB x)
    {
        return new ColorRGB(this.r + x.r, this.g + x.g, this.b + x.b);
    }
}


