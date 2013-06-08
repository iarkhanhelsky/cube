/**
 * @author Архангельский Илья
 */
package org.dmlaps.cube;

/**
 * Реализация класса "Вершина".
 * Вершина задается вектором с центром в точке (0,0,0).
 *
 * @author Ilya Arkhanhelsky.
 */
public class Vertex extends Vector
{

    public static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;

    /**
     * Конструктор создает вершину с координатами в пространстве XYZ
     *
     * @param x координата по X
     * @param y координата по Y
     * @param z координата по Z
     */
    public Vertex(double x, double y, double z)
    {
        super(x, y, z);
    }

    /**
     * Получить X координату
     *
     * @return
     */
    public double x()
    {
        return get(X);
    }


    /**
     * Получить Y координату
     *
     * @return
     */
    public double y()
    {
        return get(Y);
    }


    /**
     * Получить Z координату
     *
     * @return
     */
    public double z()
    {
        return get(Z);
    }


    /**
     * Установить норму вектора
     *
     * @param n новая норма вектора
     */
    public void setNorm(double n)
    {
        double mul = n / norm();
        mul(mul);
    }
}
