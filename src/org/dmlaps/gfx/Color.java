
package org.dmlaps.gfx;

import org.dmlaps.cube.Vector;
import org.dmlaps.cube.Vertex;

/**
 * Класс Color
 * Класс представляющий Color цвет как вектор и реализующий элементарные операции над ним
 * как над вектором (вычитание, сложение, умножение на скаляр).
 * Ни один из методов не меняет оригинал, все возвращают новые объекты.
 *
 * @author Ilya Arkhanhelsky
 */
public class Color extends Vertex
{

    public static final Color BLACK = new Color(0, 0, 0);

    /**
     * Создает цвет в пространстве Color. Каждая компонента задается диапазоном значений 0.0 - 1.0,
     * где 0.0 - значит что компонента абсолютно непрозрачна, 1.0 - абсолютно прозрачна
     *
     * @param r
     * @param g
     * @param b
     */
    public Color(double r, double g, double b)
    {
        super(r, g, b);
    }

    private Color(Vector vec)
    {
        this(vec.get(0), vec.get(1), vec.get(2));
    }

    public double r()
    {
        return x();
    }

    public double g()
    {
        return y();
    }

    public double b()
    {
        return z();
    }

    @Override
    public Color mul(double m)
    {
        return new Color(super.mul(m));
    }

    public Color sub(Color another)
    {
        return new Color(super.sub(another));
    }

    public Color sum(Color another)
    {
        return new Color(super.sum(another));
    }
}


